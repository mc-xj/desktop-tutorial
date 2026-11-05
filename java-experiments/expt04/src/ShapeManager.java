public class ShapeManager {
    private Shape[] shapes = new Shape[10]; // 最多放 10 个图形。
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
                System.out.println(shape.getColor() + "图形，面积：" + shape.getArea());
                total = total + shape.getArea();
            }
        }
        System.out.println("总面积：" + total);
    }
}
