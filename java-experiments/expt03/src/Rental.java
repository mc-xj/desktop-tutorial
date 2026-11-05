// Rental 类记录一次租车的信息。
public class Rental {
    private Car car;
    private int days;
    public Rental(Car car, int days) {
        this.car = car;
        this.days = days;
        car.rent(); // 创建订单时，把汽车标记为已租。
    }
    public int getTotalPrice() {
        return car.getPricePerDay() * days;
    }
    public void returnCar() {
        car.returnCar();
    }
}
