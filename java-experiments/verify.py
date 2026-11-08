"""维护者运行检查：JAVA_LAB_JDK 指向 JDK 9+ 的安装目录。"""
from pathlib import Path
import os, subprocess, tempfile, re
import xml.etree.ElementTree as ET
root=Path(__file__).parent
jdk=Path(os.environ['JAVA_LAB_JDK'])/'bin'
checks=[]
with tempfile.TemporaryDirectory() as tmp:
 for i,main in enumerate(['HelloWorld','WerewolfGame','RentalApp','ShapeApp','CookingApp','AttendanceApp','Agricultural','Panda'],1):
  p=root/f'expt{i:02d}'; out=Path(tmp)/str(i); out.mkdir()
  def run(cmd,stdin=''):
   result=subprocess.run(cmd,input=stdin,text=True,capture_output=True,timeout=15)
   if result.returncode: raise RuntimeError(result.stderr)
   return result.stdout
  run([str(jdk/'javac'),'--release','8','-encoding','UTF-8','-d',str(out),*map(str,(p/'src').glob('*.java'))])
  for xml in list(p.rglob('*.xml'))+list(p.glob('*.iml')): ET.parse(xml)
  def app(stdin='',args=[]): return run([str(jdk/'java'),'-Dfile.encoding=UTF-8','-cp',str(out),main,*args],stdin)
  if i==1:
   s=app()
   assert all(t in s for t in ['Hello, World!', '我是小明，今年16岁。', '第1次成绩：85', '第2次成绩：90', '第3次成绩：95'])
  if i==2:
   assert '平民胜利' in app('2\n0\n3\n1\n')
   assert '狼人胜利' in app('2\n3\n4\n')
   s=app('abc\n99\n0\n2\n2\n-1\n')
   assert all(t in s for t in ['整数编号','没有这个编号','不能淘汰同伴','已经出局'])
  if i==3:
   s=app('2\n1\n0\n1\n3\n1\n2\n1\n1\n0\n')
   assert all(t in s for t in ['还没有租车','天数应在','300 元','已经租出','还车成功','100 元'])
  if i==4:
   s=app(); assert '38.566' in s and '总面积：26.0' in s
  if i==5:
   s=app(); assert all(t in s for t in ['张三：3票','李四：2票','张三胜'])
   original=(p/'src/CookingApp.java').read_text()
   for votes,expected in [('"李四", "李四", "张三"','李四胜'),('"张三", "李四"','平局')]:
    source=Path(tmp)/'CookingApp.java'
    source.write_text(re.sub(r'String\[\] votes = \{[^}]+\}', 'String[] votes = {'+votes+'}',original))
    run([str(jdk/'javac'),'--release','8','-encoding','UTF-8','-cp',str(out),'-d',str(out),str(source)])
    assert expected in app()
  if i==6:
   for t in ['08:59:59','09:00:00']: assert '打卡成功' in app(args=[t])
   assert '打卡失败' in app(args=['09:00:01'])
   for t in ['abc','25:00:00']: assert '时间格式错误' in app(args=[t])
  if i==7: assert all(t in app() for t in ['100.10','89.90','140.57','330.57'])
  if i==8:
   for _ in range(20):
    s=app(); assert sorted(map(int,re.findall('卖出第(\\d+)件',s)))==list(range(1,11))
    assert '售卖结束，剩余0件' in s and '剩余-' not in s
  checks.append(f'- 实验{i}：Java 8 兼容编译、IDEA XML 配置检查和实际运行通过。')
  print(checks[-1],flush=True)
(root/'验证结果.md').write_text('# 高中生入门版验证结果\n\n使用 Temurin JDK 21.0.8 的 javac --release 8 编译，实际运行检查。\n\n'+'\n'.join(checks)+'\n\n检查了狼人两方胜负及非法输入；租车计费、拒绝重复出租、还车和重新出租；图形添加与移除；投票两方胜负和平局；打卡 9 点前、等于、后及非法时间；农业金额；并发售卖 20 次无重复编号或负库存。\n\n未在图形版 IDEA 中点击运行，未在实际 JDK 8 上执行。此版为教学简化版，各实验说明列出与原文差异。\n',encoding='utf-8')
