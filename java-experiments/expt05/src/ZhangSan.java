public class ZhangSan extends People implements DoFood {
    public ZhangSan() { super("张三", "清蒸鱼"); }
    @Override
    public void knife() { System.out.println("用蒸的方式做菜"); }
    @Override
    public void makeFood(String name, String kind) {
        System.out.println(name + "做的是" + kind);
    }
}
