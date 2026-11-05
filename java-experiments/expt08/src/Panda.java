public class Panda extends Thread {
    private static final Object LOCK = new Object();
    private static int remaining = 10;
    private static int sold = 0;
    public Panda(String name) { super(name); }
    @Override public void run() {
        while (true) {
            // 读取库存、检查库存、扣减库存均受同一把锁保护。
            synchronized (LOCK) {
                if (remaining <= 0) return;
                int number = ++sold;
                remaining--;
                System.out.println(getName() + "卖出了第" + number + "个冰墩墩，剩余" + remaining);
            }
            try {
                Thread.sleep(20); // 锁外短暂休眠，模拟销售耗时。
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Panda[] platforms = {new Panda("官网"), new Panda("直营店"), new Panda("授权店")};
        for (Panda platform : platforms) platform.start();
        for (Panda platform : platforms) platform.join();
        synchronized (LOCK) {
            System.out.println("冰墩墩卖完了，已售" + sold + "个，剩余" + remaining + "个");
            if (sold != 10 || remaining != 0) throw new IllegalStateException("库存不一致");
        }
    }
}
