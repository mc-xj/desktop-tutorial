import java.util.HashMap;
import java.util.Map;

public class ShapeManager {
    private final Shape[] shapes = new Shape[10]; // 最多放 10 个图形。
    private final String[] names = new String[shapes.length];
    private final Map<Class<? extends Shape>, Integer> counts = new HashMap<>();

    public void add(Shape shape) {
        if (shape == null) return;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] == null) {
                Class<? extends Shape> type = shape.getClass();
                int number = counts.getOrDefault(type, 0) + 1;
                counts.put(type, number);
                String name = type == Circle.class ? "圆形" : type.getSimpleName();
                names[i] = name + " " + number;
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
                names[i] = null;
                return;
            }
        }
    }
    public void display() {
        double total = 0;
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] != null) {
                Shape shape = shapes[i];
                System.out.println(names[i] + "｜" + shape);
                total = total + shape.getArea();
            }
        }
        System.out.printf("总面积：%.2f%n", total);
    }
}
