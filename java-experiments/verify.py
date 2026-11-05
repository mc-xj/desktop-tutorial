"""维护者校验脚本：需要 JDK 9+，JAVA_LAB_JDK 可指定 JDK 根目录。"""
from pathlib import Path
import os, subprocess, tempfile, re, xml.etree.ElementTree as ET

root = Path(__file__).parent
bin_dir = Path(os.environ['JAVA_LAB_JDK'])/'bin' if 'JAVA_LAB_JDK' in os.environ else None
javac = str(bin_dir/'javac') if bin_dir else 'javac'
java = str(bin_dir/'java') if bin_dir else 'java'
lines = []

def run(args, **kwargs):
    return subprocess.run(args, text=True, encoding='utf-8', stdout=subprocess.PIPE,
                          stderr=subprocess.PIPE, check=True, timeout=20, **kwargs).stdout

with tempfile.TemporaryDirectory(prefix='java-labs-') as tmp:
    for n in range(1, 9):
        name = 'expt%02d' % n
        project = root/name
        out = Path(tmp)/name
        out.mkdir()
        run([javac, '--release', '8', '-encoding', 'UTF-8', '-d', str(out),
             *map(str, sorted((project/'src').glob('*.java')))])
        for p in list(project.rglob('*.xml')) + list(project.glob('*.iml')):
            ET.parse(p)
        def app(main, *args, stdin=''):
            return run([java, '-Dfile.encoding=UTF-8', '-cp', str(out), main, *args], input=stdin)
        if n == 1:
            assert 'Hello, World!' in app('HelloWorld')
        elif n == 2:
            result = app('WerewolfGame', stdin='abc\n99\n0\n2\n')
            assert all(s in result for s in ['整数编号', '编号无效', '不能杀死同伴', '狼人胜利'])
            assert '游戏已退出' in app('WerewolfGame', stdin='q\n')
            harness = '''import java.util.Scanner;
public class BehaviorCheck { public static void main(String[] args) {
    if (WerewolfGame.winner(new boolean[]{false,false,true,true,true}) != 1) throw new AssertionError();
    if (WerewolfGame.winner(new boolean[]{true,false,true,true,true}) != 0) throw new AssertionError();
    int target = WerewolfGame.readTarget(new Scanner("2\\n0\\n"), new boolean[]{true,true,false,true,true}, false);
    if(target != 0) throw new AssertionError();
} }'''
        elif n == 3:
            result = app('RentalApp', stdin='rent 1 3\nrent 1 2\nrent 2 0\nreturn 1\nrent 1 1\nreturn 3\nrent 99 1\nabc\n0\n')
            assert all(s in result for s in ['300.00', '该车已租', '天数必须为正数', '还车成功', '100.00', '没有进行中的订单', '车辆编号无效', '命令格式错误'])
        elif n == 4:
            result = app('ShapeApp')
            assert all(s in result for s in ['38.57', '26.00', '36.00'])
            harness = '''public class BehaviorCheck { public static void main(String[] args) {
    try {new Triangle(1,2,3); throw new AssertionError();} catch(IllegalArgumentException expected) {}
    try {new Circle(-1); throw new AssertionError();} catch(IllegalArgumentException expected) {}
    Rectangle r = new Rectangle(4,5); r.setWidth(6);
    if(r.getArea()!=30) throw new AssertionError();
} }'''
        elif n == 5:
            result = app('CookingApp')
            assert all(s in result for s in ['张三：3票', '李四：2票', '张三胜', '主持人'])
            # 更换选票后重新编译到临时目录，验证胜者不是固定字符串。
            source = (project/'src/CookingApp.java').read_text()
            for votes, expected in [('"李四", "李四", "张三"', '李四胜'), ('"张三", "李四"', '平局')]:
                changed = re.sub(r'Arrays.asList\([^;]+\)', 'Arrays.asList('+votes+')', source)
                test_source = Path(tmp)/'CookingApp.java'
                test_source.write_text(changed)
                run([javac,'--release','8','-encoding','UTF-8','-cp',str(out),'-d',str(out),str(test_source)])
                assert expected in app('CookingApp')
        elif n == 6:
            for value in ['08:59:59', '09:00:00']:
                assert '打卡成功' in app('AttendanceApp', '2026-11-05 '+value)
            assert '超过早 9 点' in app('AttendanceApp', '2026-11-05 09:00:01')
            for value in ['2026-02-30 09:00:00', 'abc', '2026-11-05 09:00:00junk']:
                assert '时间输入错误' in app('AttendanceApp', value)
            assert '当前时间' in app('AttendanceApp')
        elif n == 7:
            result = app('Agricultural')
            assert all(s in result for s in ['100.10元', '89.90元', '140.57元', '330.57元'])
        elif n == 8:
            for _ in range(20):
                result = app('Panda')
                numbers = re.findall(r'卖出了第(\d+)个', result)
                assert sorted(map(int, numbers)) == list(range(1, 11))
                assert '卖完了，已售10个，剩余0个' in result
                assert '剩余-' not in result
        if n in (2, 4):
            source = Path(tmp)/'BehaviorCheck.java'
            source.write_text(harness, encoding='utf-8')
            run([javac,'--release','8','-encoding','UTF-8','-cp',str(out),'-d',str(out),str(source)])
            app('BehaviorCheck')
        lines.append(f'- {name}：Java 8 兼容编译、IDEA 配置 XML 检查及行为验证通过。')
        print(lines[-1], flush=True)

(root/'验证结果.md').write_text('''# 验证结果

使用 Eclipse Temurin JDK 21.0.8，通过 javac --release 8 编译全部源文件，并实际运行。
没有第三方依赖；编译输出和校验辅助类保存在临时目录，未放入交付源码。

'''+'\n'.join(lines)+'''

行为覆盖：HelloWorld 输出；狼人非法输入、同伴/死亡目标、退出及两方胜负判断；
租车计价、重复租赁、非法天数/编号和还车后重新租赁；图形增删、尺寸更新和非法三角形；
烹饪比赛张三胜、李四胜和平票；打卡 09:00:00 前/等于/后及非法日期；
农业三个部门金额和总额；预售重复 20 次，每次恰好售出编号 1～10，库存为零，无负库存。

未在图形版 IntelliJ IDEA 中点击运行，未在实际 JDK 8 上执行。IDEA 项目文件采用标准 Java 模块结构，首次打开需要选择本机 JDK。
实验一的本机 JDK/IDEA 安装步骤需用户在自己的电脑上执行。
''', encoding='utf-8')
