public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("红色");
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() { return width; }
    public void setWidth(double width) {
        requirePositive(width);
        this.width = width;
    }

    public double getHeight() { return height; }
    public void setHeight(double height) {
        requirePositive(height);
        this.height = height;
    }

    @Override
    public double getArea() { return width * height; }
}
