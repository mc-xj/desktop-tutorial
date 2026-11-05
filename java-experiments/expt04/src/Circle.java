public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        super("蓝色");
        this.radius = radius;
    }
    @Override
    public double getArea() { return Math.PI * radius * radius; }
}
