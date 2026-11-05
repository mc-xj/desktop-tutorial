public class LiSi extends People implements DoFood {
    public LiSi() { super("李四", "回锅肉"); }
    @Override
    public void knife() { System.out.println("用炒的方式做菜"); }
    @Override
    public void makeFood(String name, String kind) {
        System.out.println(name + "做的是" + kind);
    }
}
