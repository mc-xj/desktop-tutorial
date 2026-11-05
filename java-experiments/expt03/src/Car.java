public class Car {
    private final String brand, model, color;
    private final double rentPrice;
    private boolean rented;
    public Car(String brand, String model, String color, double rentPrice) {
        this(brand, model, color, rentPrice, false);
    }
    public Car(String brand, String model, String color, double rentPrice, boolean rented) {
        if (!Double.isFinite(rentPrice) || rentPrice <= 0)
            throw new IllegalArgumentException("日租金必须为正数");
        this.brand = brand; this.model = model; this.color = color;
        this.rentPrice = rentPrice; this.rented = rented;
    }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public double getRentPrice() { return rentPrice; }
    public boolean isRented() { return rented; }
    public void rent() {
        if (rented) throw new IllegalStateException("该车已租");
        rented = true;
    }
    public void returnCar() {
        if (!rented) throw new IllegalStateException("该车尚未出租");
        rented = false;
    }
}
