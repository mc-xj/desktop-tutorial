// 抽象类保存选手共同的资料，并约定烹饪方式。
public abstract class People {
    private String name;
    private String kind;

    public People() {
        this("未填写", "未填写");
    }

    public People(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }

    public abstract void knife();
}
