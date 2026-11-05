public class LiSi extends People implements DoFood {
    public LiSi() { super("李四", "回锅肉"); }
    public LiSi(String name, String kind) { super(name, kind); }
    @Override public void knife() { System.out.println("用炒的方式做菜"); }
    @Override public void makeFood(String name, String kind) {
        System.out.println("我是" + name + "，我做的是" + kind);
    }
}
