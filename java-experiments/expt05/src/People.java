public abstract class People {
    private String name, kind;
    public People() { this("未命名", "未指定"); }
    public People(String name, String kind) { this.name = name; this.kind = kind; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }
    public abstract void knife();
}
