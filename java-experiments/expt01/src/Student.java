// Student 是学生的模板，记录姓名和年龄。
public class Student {
    private String name;
    private int age;

    // 创建学生对象时，把传入的姓名和年龄保存起来。
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 方法描述对象能够做的事：介绍自己。
    public void introduce() {
        System.out.println("我是" + name + "，今年" + age + "岁。");
    }
}
