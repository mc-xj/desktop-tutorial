// 三个平台同时售卖，同一份库存只有 10 件。
public class Panda extends Thread {
    private static int remaining = 10;
    private static final Object LOCK = new Object();
    public Panda(String name) { super(name); }
    @Override
    public void run() {
        while (true) {
            // 同一时刻，只允许一个线程检查和修改库存。
            synchronized (LOCK) {
                if (remaining == 0) break;
                int number = 11 - remaining;
                remaining--;
                System.out.println(getName() + "卖出第" + number + "件，剩余" + remaining + "件");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Panda a = new Panda("官网");
        Panda b = new Panda("直营店");
        Panda c = new Panda("授权店");
        a.start(); b.start(); c.start(); // 启动三个线程。
        a.join(); b.join(); c.join(); // 等它们全部结束。
        System.out.println("售卖结束，剩余" + remaining + "件");
    }
}
