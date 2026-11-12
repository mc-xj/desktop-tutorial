// Rental 表示一笔租车记录，关联汽车和租赁天数。
public class Rental {
    private Car car;
    private int rentalDays;

    // 构造方法重载：只提供汽车时，默认租1天。
    public Rental(Car car) {
        this(car, 1);
    }

    // 提供汽车和天数时，按指定天数创建记录。
    public Rental(Car car, int rentalDays) {
        setCar(car);
        setRentalDays(rentalDays);
    }

    public Car getCar() { return car; }
    public void setCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("汽车不能为空");
        }
        this.car = car;
    }
    public int getRentalDays() { return rentalDays; }
    public void setRentalDays(int rentalDays) {
        if (rentalDays < 1 || rentalDays > 365) {
            throw new IllegalArgumentException("租赁天数应为1到365");
        }
        this.rentalDays = rentalDays;
    }

    // 总租金由日租金和天数决定，直接计算，不单独设置。
    public int getTotalPrice() {
        return car.getRentPrice() * rentalDays;
    }

    // 创建记录和真正租车分开，便于看清状态变化。
    public void rentCar() { car.rent(); }
    public void returnCar() { car.returnCar(); }
}
