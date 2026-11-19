public class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        super("绿色");
        setSides(a, b, c);
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    public void setSides(double a, double b, double c) {
        requirePositive(a);
        requirePositive(b);
        requirePositive(c);
        // 三边必须同时满足三角形不等式。
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("任意两边之和必须大于第三边");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
