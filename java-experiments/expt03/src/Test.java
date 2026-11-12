import java.util.Scanner;

// 测试类负责输入、创建对象、调用方法和输出结果。
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 方式一：无参构造 + setter，先有对象，再填写属性。
        Car car = new Car();
        car.setBrand("丰田");
        car.setModel("卡罗拉");
        car.setColor("白色");
        car.setRentPrice(100);

        // 方式二：有参构造，创建时直接填写全部资料。
        Car anotherCar = new Car("本田", "思域", "黑色", 80);
        System.out.println("构造方法示例：" + anotherCar.getBrand()
                + " " + anotherCar.getModel() + "，每天"
                + anotherCar.getRentPrice() + "元（只展示，不参与本次租赁）");

        Rental order = null; // null 表示还没有进行中的订单。
        while (true) {
            System.out.println("\n【汽车租赁系统】");
            System.out.println("当前汽车：" + car.getBrand() + " "
                    + car.getModel() + " " + car.getColor());
            System.out.println("每天租金：" + car.getRentPrice() + "元");
            System.out.println("是否已租：" + car.isRented());
            System.out.println("1 租车，2 还车，0 退出");
            if (!input.hasNext()) break;
            if (!input.hasNextInt()) {
                System.out.println("请输入数字菜单。");
                input.next(); // 取走错误输入，下一轮才能读到新内容。
                continue;
            }
            int choice = input.nextInt();
            if (choice == 0) break;

            if (choice == 1) {
                if (car.isRented()) {
                    System.out.println("这辆车已经租出，请先还车。");
                    continue;
                }
                System.out.println("请输入租赁天数（1到365）：");
                if (!input.hasNext()) break;
                if (!input.hasNextInt()) {
                    System.out.println("天数必须是整数。");
                    input.next();
                    continue;
                }
                int days = input.nextInt();
                if (days < 1 || days > 365) {
                    System.out.println("天数应在1到365之间。");
                    continue;
                }
                order = new Rental(car, days);
                order.rentCar();
                System.out.println("租车成功，租赁" + order.getRentalDays() + "天");
                System.out.println("总租金：" + order.getTotalPrice() + "元");
            } else if (choice == 2) {
                if (order == null) {
                    System.out.println("当前没有需要归还的汽车。");
                } else {
                    order.returnCar();
                    order = null;
                    System.out.println("还车成功。");
                }
            } else {
                System.out.println("请选择0、1或2。");
            }
        }
        System.out.println("程序结束。");
    }
}
