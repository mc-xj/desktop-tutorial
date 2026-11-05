public class CookingApp {
    public static void main(String[] args) {
        ZhangSan zhang = new ZhangSan();
        LiSi li = new LiSi();
        System.out.println("比赛开始！");
        zhang.makeFood(zhang.getName(), zhang.getKind());
        zhang.knife();
        li.makeFood(li.getName(), li.getKind());
        li.knife();
        String[] votes = {"张三", "李四", "张三", "李四", "张三"};
        int zhangVotes = 0;
        int liVotes = 0;
        for (int i = 0; i < votes.length; i++) {
            if (votes[i].equals("张三")) {
                zhangVotes++;
            } else if (votes[i].equals("李四")) {
                liVotes++;
            }
        }
        System.out.println("张三：" + zhangVotes + "票");
        System.out.println("李四：" + liVotes + "票");
        if (zhangVotes > liVotes) {
            System.out.println("张三胜！");
        } else if (zhangVotes < liVotes) {
            System.out.println("李四胜！");
        } else {
            System.out.println("平局！");
        }
        // 选学：Lambda 是接口实现的一种简短写法，先会运行即可。
        DoFood host = (name, kind) -> System.out.println(name + "宣布：" + kind);
        host.makeFood("主持人", "比赛结束");
    }
}
