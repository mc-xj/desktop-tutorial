public class ShapeApp {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4, 5); // 向上转型
        Shape circle = new Circle(2);
        Triangle triangle = new Triangle(3, 4, 5);
        ShapeManager manager = new ShapeManager();
        manager.add(rectangle);
        manager.add(circle);
        manager.add(triangle);
        System.out.println("添加后：");
        manager.display();

        // 两段内容相同的文字可以来自不同对象。
        String color = new String("红色");
        System.out.println("比较颜色引用：" + (rectangle.getColor() == color));
        System.out.println("比较颜色内容：" + rectangle.getColor().equals(color));

        manager.remove(circle);
        System.out.println("移除圆形后：");
        manager.display();

        try {
            triangle.setSides(1, 2, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("三角形校验失败：" + e.getMessage());
            System.out.println("原三角形面积仍为：" + triangle.getArea());
        }
    }
}
