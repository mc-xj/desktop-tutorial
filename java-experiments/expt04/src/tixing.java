public class tixing extends Shape {
  //  private static final String color = ;
        private double a;
        private double b;
        private double c;

    public tixing(double a, double b, double c) {
        super("红色");
        setShangdi(a);
        setXiadi(b);
        setGao(c);
    }

    public double getA() { return a; }
        public double getB() { return b; }
        public double getC() { return c; }

    public void setShangdi(double a) {
        requirePositive(a);
        if (a == 0) {
            throw new IllegalArgumentException("上底不能为0");
        }
        this.a = a;
    }
    public void setXiadi(double b) {
        requirePositive(b);
        if (b == 0) {
            throw new IllegalArgumentException("下底不能为0");
        }
        this.b = b;
    }
    public void setGao(double c) {
        requirePositive(c);
        if (c == 0) {
            throw new IllegalArgumentException("高不能为0");
        }
        this.c = c;
    }

        @Override
        public double getArea() {
           double p = (a + b ) * c / 2;
           return p;
           // return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
    }

