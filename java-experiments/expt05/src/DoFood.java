// 只有一个抽象方法的接口，可以使用 Lambda 实现。
@FunctionalInterface
public interface DoFood {
    void makeFood(String name, String kind);
}
