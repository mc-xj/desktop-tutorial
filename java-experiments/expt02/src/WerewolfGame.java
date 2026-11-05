import java.util.Arrays;
import java.util.Scanner;

/** 教学简化版：公开角色，由操作者输入夜间目标和白天投票目标。 */
public class WerewolfGame {
    static final String[] ROLES = {"狼人", "狼人", "平民", "平民", "平民"};
    static int winner(boolean[] alive) {
        int wolves = 0, villagers = 0;
        for (int i = 0; i < ROLES.length; i++) {
            if (alive[i]) {
                if ("狼人".equals(ROLES[i])) wolves++; else villagers++;
            }
        }
        if (wolves == 0) return 1;
        return wolves >= villagers ? 2 : 0;
    }
    static int readTarget(Scanner input, boolean[] alive, boolean night) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if ("q".equalsIgnoreCase(line)) return -1;
            try {
                int target = Integer.parseInt(line);
                if (target < 0 || target >= alive.length || !alive[target]) {
                    System.out.println("编号无效或该玩家已死亡，请重输：");
                } else if (night && "狼人".equals(ROLES[target])) {
                    System.out.println("不能杀死同伴，请重输：");
                } else return target;
            } catch (NumberFormatException e) {
                System.out.println("请输入整数编号或 q：");
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        boolean[] alive = new boolean[ROLES.length];
        Arrays.fill(alive, true);
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到狼人杀游戏！输入 q 退出。");
        while (winner(alive) == 0) {
            System.out.println("当前存活玩家：");
            for (int i = 0; i < alive.length; i++)
                if (alive[i]) System.out.println(i + ": " + ROLES[i]);
            System.out.println("狼人行动，输入要杀死的玩家编号：");
            int target = readTarget(input, alive, true);
            if (target == -1) { System.out.println("游戏已退出。"); return; }
            alive[target] = false;
            System.out.println("玩家 " + target + " 已被杀死。");
            if (winner(alive) != 0) break;
            System.out.println("天亮了，请发言并投票，输入目标编号：");
            target = readTarget(input, alive, false);
            if (target == -1) { System.out.println("游戏已退出。"); return; }
            alive[target] = false;
            System.out.println("玩家 " + target + " 已被投票出局。");
        }
        System.out.println(winner(alive) == 1 ? "平民胜利！" : "狼人胜利！");
        System.out.println("游戏结束。");
    }
}
