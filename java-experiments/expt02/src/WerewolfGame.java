import java.util.Scanner;

// 这是公开身份的练习小游戏，用来学习数组、循环和判断。
public class WerewolfGame {
    public static void main(String[] args) {
        // 2 个狼人、5 个平民。增加平民，让大家能体验白天投票。
        String[] roles = {"狼人", "狼人", "平民", "平民", "平民", "平民", "平民"};
        boolean[] alive = {true, true, true, true, true, true, true};
        Scanner input = new Scanner(System.in);
        boolean night = true; // true 是夜晚，false 是白天。
        System.out.println("欢迎来到狼人杀！输入 -1 可以退出。");
        while (true) {
            int wolves = 0;
            int villagers = 0;
            System.out.println("存活玩家（编号从 0 开始）：");
            for (int i = 0; i < roles.length; i++) {
                if (alive[i]) {
                    System.out.println(i + "号：" + roles[i]);
                    if (roles[i].equals("狼人")) {
                        wolves++;
                    } else {
                        villagers++;
                    }
                }
            }
            if (wolves == 0) {
                System.out.println("平民胜利！");
                break;
            }
            if (wolves >= villagers) {
                System.out.println("狼人胜利！");
                break;
            }
            if (night) {
                System.out.println("夜晚：狼人要淘汰谁？");
            } else {
                System.out.println("白天：大家要投票淘汰谁？");
            }
            // 先检查输入，防止输入文字导致程序出错。
            if (!input.hasNext()) break;
            if (!input.hasNextInt()) {
                System.out.println("请输入整数编号。");
                input.next();
                continue;
            }
            int target = input.nextInt();
            if (target == -1) break;
            if (target < 0 || target >= roles.length) {
                System.out.println("没有这个编号，请重新输入。");
                continue;
            }
            if (!alive[target]) {
                System.out.println("这个玩家已经出局了。");
                continue;
            }
            if (night && roles[target].equals("狼人")) {
                System.out.println("狼人不能淘汰同伴。");
                continue;
            }
            alive[target] = false;
            System.out.println(target + "号玩家出局。");
            night = !night; // 夜晚变白天，白天变夜晚。
        }
        System.out.println("游戏结束。");
    }
}
