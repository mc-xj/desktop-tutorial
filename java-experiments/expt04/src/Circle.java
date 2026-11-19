public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("蓝色");
        setRadius(radius);
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) {
        requirePositive(radius);
        this.radius = radius;
    }

    @Override
    public double getArea() { return Math.PI * radius * radius; }
}
