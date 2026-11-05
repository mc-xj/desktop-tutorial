public class ShapeApp {
    public static void main(String[] args) {
        ShapeManager manager = new ShapeManager();
        Rectangle rectangle = new Rectangle(4, 5);
        rectangle.setColor("红色"); rectangle.setFill(true);
        Circle circle = new Circle(2);
        manager.add(rectangle); manager.add(circle); manager.add(new Triangle(3, 4, 5));
        System.out.println("添加三个图形后："); manager.display();
        manager.remove(circle);
        System.out.println("移除圆形后："); manager.display();
        rectangle.setWidth(6);
        System.out.println("修改矩形宽度后："); manager.display();
    }
}
