import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Agricultural {
    // 用“分”存金额，例如 5020 分就是 50.20 元，避免小数计算误差。
    public static int sum(ArrayList<Integer> money) {
        int total = 0;
        Iterator<Integer> it = money.iterator();
        while (it.hasNext()) {
            total = total + it.next();
        }
        return total;
    }
    public static void main(String[] args) {
        ArrayList<Integer> rice = new ArrayList<Integer>();
        rice.add(5020); rice.add(2958); rice.add(2032);
        ArrayList<Integer> wheat = new ArrayList<Integer>();
        wheat.add(1312); wheat.add(6666); wheat.add(1012);
        ArrayList<Integer> corn = new ArrayList<Integer>();
        corn.add(3032); corn.add(9532); corn.add(1493);
        Map<String, Integer> totals = new LinkedHashMap<String, Integer>();
        totals.put("水稻", sum(rice));
        totals.put("小麦", sum(wheat));
        totals.put("玉米", sum(corn));
        int all = 0;
        for (String name : totals.keySet()) {
            int money = totals.get(name);
            System.out.printf("%s部门：%.2f元%n", name, money / 100.0);
            all = all + money;
        }
        System.out.printf("本月合计：%.2f元%n", all / 100.0);
    }
}
