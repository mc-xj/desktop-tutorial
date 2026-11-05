// 抽象类不能直接 new，它是给具体选手使用的模板。
public abstract class People {
    private String name;
    private String kind;
    public People(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }
    public String getName() { return name; }
    public String getKind() { return kind; }
    public abstract void knife();
}
