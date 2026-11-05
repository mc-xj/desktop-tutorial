// Car 类就像一张汽车信息卡。
public class Car {
    private String brand;
    private int pricePerDay;
    private boolean rented;

    public Car(String brand, int pricePerDay) {
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        rented = false;
    }
    public String getBrand() { return brand; }
    public int getPricePerDay() { return pricePerDay; }
    public boolean isRented() { return rented; }
    public void rent() { rented = true; }
    public void returnCar() { rented = false; }
}
