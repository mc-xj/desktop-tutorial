import java.util.ArrayList;
import java.util.List;

// 比赛入口：展示菜品、保存选票、逐票显示、统计并公布结果。
public class Test {
    public static void main(String[] args) {
        ZhangSan zhangSan = new ZhangSan();
        LiSi liSi = new LiSi("李四", "回锅肉");
        System.out.println("-------比赛开始-------");
        zhangSan.makeFood(zhangSan.getName(), zhangSan.getKind());
        zhangSan.knife();
        liSi.makeFood(liSi.getName(), liSi.getKind());
        liSi.knife();

        // DoFood 是函数式接口，箭头右侧是 makeFood 的实现。
        DoFood host = (name, kind) -> System.out.println(name + "宣布：" + kind);
        host.makeFood("主持人", "比赛结束，评委投票");

        List<String> votes = new ArrayList<>();
        if (args.length == 0) {
            votes.add("张三");
            votes.add("李四");
            votes.add("张三");
            votes.add("李四");
            votes.add("张三");
        } else {
            // 可在 IDEA 的 Program arguments 填入选手姓名，以空格分隔。
            for (String name : args) {
                votes.add(name);
            }
        }

        // 每张票只能属于参加比赛的选手。
        for (String vote : votes) {
            if (!zhangSan.getName().equals(vote) && !liSi.getName().equals(vote)) {
                System.out.println("无效选票：" + vote + "，请使用张三或李四");
                return;
            }
        }

        System.out.println("评委选票：");
        votes.forEach(v -> System.out.println(v));
        long zhangVotes = votes.stream()
                .filter(v -> zhangSan.getName().equals(v))
                .count();
        long liVotes = votes.stream()
                .filter(v -> liSi.getName().equals(v))
                .count();

        System.out.println("张三：" + zhangVotes + "票");
        System.out.println("李四：" + liVotes + "票");
        String result = zhangVotes == liVotes
                ? "平局" : (zhangVotes > liVotes ? "张三胜" : "李四胜");
        System.out.println("比赛结果：" + result);
    }
}
