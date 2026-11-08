// 类名 HelloWorld 必须和文件名相同。
public class HelloWorld {
    // 程序从 main 方法开始运行，先记住这个固定写法。
    public static void main(String[] args) {
        // println 表示输出一行文字。
        System.out.println("Hello, World!");
        System.out.println("我是高中生，我正在学习 Java！");

        // 类是模板，对象是根据模板创建的具体事物。
        Student student = new Student("小明", 16);
        student.introduce();

        // 数组把多个同类型的数据放在一起，下标从 0 开始。
        int[] scores = {85, 90, 95};
        // 从第 0 格开始，每次向后移动一格，直到数组末尾。
        for (int i = 0; i < scores.length; i++) {
            System.out.println("第" + (i + 1) + "次成绩：" + scores[i]);
        }
    }
}
