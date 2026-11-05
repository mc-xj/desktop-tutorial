import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class Agricultural {
    static List<BigDecimal> amounts(String... values) {
        BigDecimal[] result = new BigDecimal[values.length];
        for (int i = 0; i < values.length; i++) result[i] = new BigDecimal(values[i]);
        return Arrays.asList(result);
    }
    static BigDecimal sum(List<BigDecimal> list) {
        BigDecimal total = BigDecimal.ZERO;
        Iterator<BigDecimal> iterator = list.iterator();
        while (iterator.hasNext()) total = total.add(iterator.next());
        return total;
    }
    public static void main(String[] args) {
        List<BigDecimal> rice = amounts("50.20", "29.58", "20.32");
        List<BigDecimal> wheat = amounts("13.12", "66.66", "10.12");
        List<BigDecimal> corn = amounts("30.32", "95.32", "14.93");
        Map<String, BigDecimal> budget = new LinkedHashMap<>();
        budget.put("水稻", sum(rice)); budget.put("小麦", sum(wheat)); budget.put("玉米", sum(corn));
        BigDecimal total = BigDecimal.ZERO;
        Iterator<Map.Entry<String, BigDecimal>> entries = budget.entrySet().iterator();
        System.out.println("本月实际报销统计");
        while (entries.hasNext()) {
            Map.Entry<String, BigDecimal> entry = entries.next();
            System.out.println(entry.getKey() + "部门：" + entry.getValue().toPlainString() + "元");
            total = total.add(entry.getValue());
        }
        System.out.println("本月合计：" + total.toPlainString() + "元");
    }
}
