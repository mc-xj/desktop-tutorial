public abstract class Shape {
    private String color;

    protected Shape(String color) {
        setColor(color);
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    protected static void requirePositive(double size) {
        if (!Double.isFinite(size) || size <= 0) {
            throw new IllegalArgumentException("尺寸必须是有限正数");
        }
    }

    public abstract double getArea();



    @Override
    public String toString() {
        return String.format("%s，颜色：%s，面积：%.2f",
                getClass().getSimpleName(), getColor(), getArea());
    }
}
