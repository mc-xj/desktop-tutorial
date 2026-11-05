public class Rental {
    private final int rentalDays;
    private final Car car;
    private final double totalPrice;
    private boolean returned;
    public Rental(Car car, int rentalDays) {
        if (car == null || rentalDays <= 0)
            throw new IllegalArgumentException("车辆不能为空，天数必须为正数");
        this.car = car; this.rentalDays = rentalDays;
        this.totalPrice = calculateRent();
        car.rent();
    }
    public double calculateRent() { return rentalDays * car.getRentPrice(); }
    public int getRentalDays() { return rentalDays; }
    public Car getCar() { return car; }
    public double getTotalPrice() { return totalPrice; }
    public void returnCar() {
        if (returned) throw new IllegalStateException("此订单已还车");
        car.returnCar(); returned = true;
    }
}
