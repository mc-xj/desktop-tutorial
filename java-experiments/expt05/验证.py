from pathlib import Path
import os, subprocess, tempfile
root=Path(__file__).parent
jdk=Path(os.environ['JAVA_LAB_JDK'])/'bin' if 'JAVA_LAB_JDK' in os.environ else None
def run(cmd):
 p=subprocess.run(cmd,capture_output=True,text=True,encoding='utf-8',timeout=20)
 if p.returncode:raise RuntimeError(p.stderr)
 return p.stdout
with tempfile.TemporaryDirectory() as folder:
 run([str(jdk/'javac') if jdk else 'javac','--release','8','-encoding','UTF-8','-d',folder,*map(str,(root/'src').glob('*.java'))])
 for entry in ['Test','CookingApp']:
  for args,expected in [([],['张三：3票','李四：2票','张三胜']),(['李四','李四','张三'],['张三：1票','李四：2票','李四胜']),(['张三','李四'],['平局']),(['王五'],['无效选票']),(['李四'],['李四：1票','李四胜'])]:
   s=run([str(jdk/'java') if jdk else 'java','-Dfile.encoding=UTF-8','-cp',folder,entry,*args])
   assert all(x in s for x in expected)
   if args==['王五']:assert '比赛结果：' not in s
  s=run([str(jdk/'java') if jdk else 'java','-Dfile.encoding=UTF-8','-cp',folder,entry])
  assert all(x in s for x in ['用蒸','用炒','主持人','清蒸鱼','回锅肉'])
  assert s.split('评委选票：')[1].split('张三：')[0].strip().splitlines()==['张三','李四','张三','李四','张三']
print('Java 8兼容编译、两个入口、选票顺序、两方胜负、平局及无效票检查通过。')
