// Car 表示一辆汽车，属性私有，通过方法访问。
public class Car {
    private String brand;       // 品牌
    private String model;       // 车型
    private String color;       // 颜色
    private int rentPrice;      // 每天租金，单位：元
    private boolean rented;     // 是否已经租出

    // 无参构造方法：先创建一辆车，再通过 setter 填资料。
    public Car() {
        this("未填写", "未填写", "未填写", 100);
    }

    // 有参构造方法：创建时一次填好资料。
    public Car(String brand, String model, String color, int rentPrice) {
        setBrand(brand);
        setModel(model);
        setColor(color);
        setRentPrice(rentPrice);
        rented = false;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getRentPrice() { return rentPrice; }
    public void setRentPrice(int rentPrice) {
        if (rentPrice <= 0) {
            throw new IllegalArgumentException("日租金必须大于0");
        }
        this.rentPrice = rentPrice;
    }
    // boolean 类型通常使用 is 开头的 getter。
    public boolean isRented() { return rented; }
    public void setRented(boolean rented) { this.rented = rented; }

    public void rent() { setRented(true); }
    public void returnCar() { setRented(false); }
}
