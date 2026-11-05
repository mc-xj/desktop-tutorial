public class Rectangle extends Shape {
    private double width, height;
    public Rectangle(double width, double height) { setWidth(width); setHeight(height); }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public void setWidth(double width) { this.width = positive(width); }
    public void setHeight(double height) { this.height = positive(height); }
    @Override public double area() { return width * height; }
}
