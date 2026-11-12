import java.util.Scanner;

// 用两个下标对应的数组，管理多辆汽车与各自的租赁记录。
public class Test {
    // 重复读取，直到得到整数；输入流结束时返回0。
    public static int readInt(Scanner input) {
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                return input.nextInt();
            }
            System.out.println("请输入整数。");
            input.next(); // 丢弃错误输入，避免反复读取同一内容。
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 数组有3个格子，创建后格子内先是null，还没有汽车对象。
        Car[] cars = new Car[3];

        // 第一辆演示无参构造 + setter。
        cars[0] = new Car();
        cars[0].setBrand("丰田");
        cars[0].setModel("卡罗拉");
        cars[0].setColor("白色");
        cars[0].setRentPrice(100);
        // 其他两辆演示有参构造，三辆车都能实际租赁。
        cars[1] = new Car("本田", "思域", "黑色", 80);
        cars[2] = new Car("福特", "福克斯", "银色", 90);

        // orders[i] 保存cars[i]的订单，null表示这辆车没有进行中的订单。
        Rental[] orders = new Rental[cars.length];
        while (true) {
            System.out.println("\n【汽车租赁系统】");
            // 用户编号从1开始；数组下标从0开始。
            for (int i = 0; i < cars.length; i++) {
                Car car = cars[i];
                System.out.print((i + 1) + ". " + car.getBrand() + " "
                        + car.getModel() + " " + car.getColor()
                        + " 每天" + car.getRentPrice() + "元 ");
                if (car.isRented()) {
                    System.out.println("已租");
                } else {
                    System.out.println("可租");
                }
            }
            System.out.println("1 租车，2 还车，0 退出");
            int choice = readInt(input);
            if (choice == 0) break;
            if (choice != 1 && choice != 2) {
                System.out.println("请选择0、1或2。");
                continue;
            }

            System.out.println("请输入车辆编号（1到" + cars.length + "，0返回菜单）：");
            int number = readInt(input);
            if (number == 0) continue;
            // 先检查范围，再访问数组，避免数组下标越界。
            if (number < 1 || number > cars.length) {
                System.out.println("车辆编号无效。");
                continue;
            }
            int index = number - 1;
            Car car = cars[index];
            if (choice == 1) {
                if (car.isRented()) {
                    System.out.println("这辆车已经租出，请先还车。");
                    continue;
                }
                System.out.println("请输入租赁天数（1到365，0返回菜单）：");
                int days = readInt(input);
                if (days == 0) continue;
                if (days < 1 || days > 365) {
                    System.out.println("天数应在1到365之间。");
                    continue;
                }
                orders[index] = new Rental(car, days);
                orders[index].rentCar();
                System.out.println("租车成功：" + number + "号车，租赁"
                        + orders[index].getRentalDays() + "天");
                System.out.println("总租金：" + orders[index].getTotalPrice() + "元");
            } else {
                if (orders[index] == null) {
                    System.out.println("这辆车没有需要归还的订单。");
                } else {
                    orders[index].returnCar();
                    orders[index] = null;
                    System.out.println("还车成功：" + number + "号车。");
                }
            }
        }
        System.out.println("程序结束。");
    }
}
