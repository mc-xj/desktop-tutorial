public class ZhangSan extends People implements DoFood {
    public ZhangSan() { super("张三", "清蒸鱼"); }
    public ZhangSan(String name, String kind) { super(name, kind); }
    @Override public void knife() { System.out.println("用蒸的方式做菜"); }
    @Override public void makeFood(String name, String kind) {
        System.out.println("我是" + name + "，我做的是" + kind);
    }
}
