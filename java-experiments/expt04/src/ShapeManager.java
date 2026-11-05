import java.util.ArrayList;
import java.util.List;
public class ShapeManager {
    private final List<Shape> shapes = new ArrayList<>();
    public void add(Shape shape) {
        if (shape == null) throw new IllegalArgumentException("图形不能为空");
        shapes.add(shape);
    }
    public boolean remove(Shape shape) { return shapes.remove(shape); }
    public double getTotalArea() {
        double total = 0;
        for (Shape shape : shapes) total += shape.getArea();
        return total;
    }
    public void display() {
        for (Shape s : shapes) System.out.printf("%s 颜色=%s 填充=%s 边框=%.1f 面积=%.2f%n",
            s.getClass().getSimpleName(), s.getColor(), s.isFill(), s.getBorderWidth(), s.getArea());
        System.out.printf("总面积：%.2f%n", getTotalArea());
    }
}
