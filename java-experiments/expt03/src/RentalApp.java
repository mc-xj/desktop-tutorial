import java.util.Scanner;
public class RentalApp {
    public static void main(String[] args) {
        // 先只学习一辆车，避免同时处理太多信息。
        Car car = new Car("丰田", 100);
        Rental order = null;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("汽车：" + car.getBrand() + "，每天 " + car.getPricePerDay() + " 元");
            System.out.println("是否已租：" + car.isRented());
            System.out.println("1 租车，2 还车，0 退出");
            if (!input.hasNext()) break;
            if (!input.hasNextInt()) {
                System.out.println("请输入数字。"); input.next(); continue;
            }
            int choice = input.nextInt();
            if (choice == 0) break;
            if (choice == 1) {
                if (car.isRented()) {
                    System.out.println("汽车已经租出去了。");
                    continue;
                }
                System.out.println("要租几天？请输入 1 到 365 的整数：");
                if (!input.hasNext()) break;
                if (!input.hasNextInt()) {
                    System.out.println("天数必须是整数。"); input.next(); continue;
                }
                int days = input.nextInt();
                if (days < 1 || days > 365) {
                    System.out.println("天数应在 1 到 365 之间。"); continue;
                }
                order = new Rental(car, days);
                System.out.println("租车成功，总租金：" + order.getTotalPrice() + " 元");
            } else if (choice == 2) {
                if (order == null) {
                    System.out.println("还没有租车。");
                } else {
                    order.returnCar(); order = null;
                    System.out.println("还车成功。");
                }
            } else {
                System.out.println("请选择 0、1 或 2。");
            }
        }
    }
}
