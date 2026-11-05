import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class CookingApp {
    public static void main(String[] args) {
        System.out.println("-------比赛开始-------");
        People[] contestants = {new ZhangSan(), new LiSi()};
        for (People p : contestants) {
            ((DoFood) p).makeFood(p.getName(), p.getKind());
            p.knife();
        }
        // 函数式接口的 Lambda 实现：没有额外创建实现类。
        DoFood host = (name, kind) -> System.out.println("主持人：" + name + "展示完" + kind);
        host.makeFood("两位选手", "拿手菜");
        List<String> votes = Arrays.asList("张三", "李四", "张三", "李四", "张三");
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("张三", 0); counts.put("李四", 0);
        votes.forEach(name -> {
            if (!counts.containsKey(name)) throw new IllegalArgumentException("未知选手：" + name);
            counts.put(name, counts.get(name) + 1);
        });
        counts.forEach((name, count) -> System.out.println(name + "：" + count + "票"));
        int zhang = counts.get("张三"), li = counts.get("李四");
        System.out.println(zhang == li ? "平局" : (zhang > li ? "张三胜" : "李四胜"));
    }
}
