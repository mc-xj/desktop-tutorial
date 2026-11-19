public class ShapeManager {
    private final Shape[] shapes = new Shape[10]; // 最多放 10 个图形。
    public void add(Shape shape) {
        if (shape == null) return;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == null) {
                shapes[i] = shape;
                return;
            }
        }
        System.out.println("已经放满了。");
    }
    public void remove(Shape shape) {
        if (shape == null) return;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == shape) {
                shapes[i] = null;
                return;
            }
        }
    }
    public void display() {
        double total = 0;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] != null) {
                Shape shape = shapes[i];
                System.out.println(shape);
                total = total + shape.getArea();
            }
        }
        System.out.printf("总面积：%.2f%n", total);
    }
}
