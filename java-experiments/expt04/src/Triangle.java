public class Triangle extends Shape {
    private double a, b, c;
    public Triangle(double a, double b, double c) { setSides(a, b, c); }
    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public void setSides(double a, double b, double c) {
        positive(a); positive(b); positive(c);
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("三边不能组成三角形");
        this.a = a; this.b = b; this.c = c;
    }
    @Override public double area() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
