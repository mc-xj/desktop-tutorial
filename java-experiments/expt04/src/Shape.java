// 父类：把所有图形都有的内容放在一起。
public abstract class Shape {
    private String color;
    public Shape(String color) { this.color = color; }
    public String getColor() { return color; }
    // 每种图形的面积公式不同，所以这里不写具体公式。
    public abstract double getArea();
}
