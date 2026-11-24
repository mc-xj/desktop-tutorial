public class ShapeApp {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4, 5); // 向上转型
        Shape circle = new Circle(2);
        Triangle triangle = new Triangle(3, 4, 5);
        Shape tixing = new tixing(4, 5,2);
        Shape circle2 = new Circle(4);
        ShapeManager manager = new ShapeManager();


        manager.add(rectangle);
        manager.add(circle);
        manager.add(triangle);
        manager.add(circle2);
        manager.add(tixing);
        System.out.println("智能图形设计师");
        System.out.println("\n添加图形后：");
        manager.display();

        // 两段内容相同的文字可以来自不同对象。
        String color = new String("红色");
        System.out.println("比较颜色引用：" + (rectangle.getColor() == color));
        System.out.println("比较颜色内容：" + rectangle.getColor().equals(color));

        manager.remove(circle);
        manager.remove(triangle);

        System.out.println("移除图形后：");
        manager.display();

        double newA = 1;
        double newB = 2;
        double newC = 3;

        try {
            triangle.setSides(newA, newB, newC);
        } catch (IllegalArgumentException e) {
            System.out.printf("\n尝试设置的三边：%.1g、%.1g、%.1g%n", newA, newB, newC);
            System.out.println("三角形校验失败：" + e.getMessage());
            System.out.println("原三角形面积仍为：" + triangle.getArea());
        }
    }
}
