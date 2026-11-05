public class Circle extends Shape {
    private double radius;
    public Circle(double radius) { setRadius(radius); }
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = positive(radius); }
    @Override public double area() { return Math.PI * radius * radius; }
}
