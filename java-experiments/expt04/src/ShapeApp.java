public class ShapeApp {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(2);
        Triangle triangle = new Triangle(3, 4);
        ShapeManager manager = new ShapeManager();
        manager.add(rectangle);
        manager.add(circle);
        manager.add(triangle);
        System.out.println("添加后：");
        manager.display();
        manager.remove(circle);
        System.out.println("移除圆形后：");
        manager.display();
    }
}
