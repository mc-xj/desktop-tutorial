public abstract class Shape {
    private String color = "黑色";
    private boolean fill;
    private double borderWidth = 1;
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public boolean isFill() { return fill; }
    public void setFill(boolean fill) { this.fill = fill; }
    public double getBorderWidth() { return borderWidth; }
    public void setBorderWidth(double value) {
        if (!Double.isFinite(value) || value < 0) throw new IllegalArgumentException("边框宽度必须非负");
        borderWidth = value;
    }
    protected static double positive(double value) {
        if (!Double.isFinite(value) || value <= 0) throw new IllegalArgumentException("尺寸必须是有限正数");
        return value;
    }
    public abstract double area();
    public double getArea() { return area(); }
}
