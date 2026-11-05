import java.util.Scanner;
public class RentalApp {
    public static void main(String[] args) {
        Car[] cars = { new Car("Toyota", "Camry", "黑色", 100),
            new Car("Honda", "Civic", "红色", 80), new Car("Ford", "Focus", "银色", 90) };
        Rental[] orders = new Rental[cars.length];
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("【汽车租赁系统】命令：rent 编号 天数 / return 编号 / 0 退出");
            for (int i = 0; i < cars.length; i++) {
                Car c = cars[i];
                System.out.printf("%d. %s %s %s %.2f元/天 %s%n", i + 1,
                    c.getBrand(), c.getModel(), c.getColor(), c.getRentPrice(),
                    c.isRented() ? "已租" : "可租");
            }
            if (!input.hasNextLine()) break;
            String line = input.nextLine().trim();
            if ("0".equals(line)) break;
            String[] parts = line.split("\\s+");
            try {
                if (parts.length < 2) throw new IllegalArgumentException("命令格式错误");
                int index = Integer.parseInt(parts[1]) - 1;
                if (index < 0 || index >= cars.length) throw new IllegalArgumentException("车辆编号无效");
                if ("rent".equals(parts[0]) && parts.length == 3) {
                    Rental order = new Rental(cars[index], Integer.parseInt(parts[2]));
                    orders[index] = order;
                    System.out.printf("租赁成功，共 %d 天，总租金：%.2f元%n", order.getRentalDays(), order.getTotalPrice());
                } else if ("return".equals(parts[0]) && parts.length == 2) {
                    if (orders[index] == null) throw new IllegalStateException("没有进行中的订单");
                    orders[index].returnCar(); orders[index] = null;
                    System.out.println("还车成功");
                } else throw new IllegalArgumentException("命令格式错误");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("操作失败：" + e.getMessage());
            }
        }
    }
}
