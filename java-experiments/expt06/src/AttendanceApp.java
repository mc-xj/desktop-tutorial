import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
public class AttendanceApp {
    public static void check(Date now, Date deadline) {
        if (now.after(deadline)) throw new MyException("超过早 9 点不允许打卡");
    }
    public static void main(String[] args) {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        full.setLenient(false); full.setTimeZone(zone); day.setTimeZone(zone);
        try {
            Date now = new Date();
            if (args.length > 1) throw new IllegalArgumentException("只允许一个时间参数");
            if (args.length == 1) {
                // 格式化回写校验，拒绝额外字符和不完整输入。
                now = full.parse(args[0]);
                if (!full.format(now).equals(args[0])) throw new IllegalArgumentException("时间格式应为 yyyy-MM-dd HH:mm:ss");
            }
            Date deadline = full.parse(day.format(now) + " 09:00:00");
            System.out.println("时区：Asia/Shanghai");
            System.out.println("当前时间：" + full.format(now));
            System.out.println("截止时间：" + full.format(deadline));
            check(now, deadline);
            System.out.println("打卡成功");
        } catch (MyException e) {
            System.out.println("打卡异常：" + e.getMessage());
        } catch (ParseException | IllegalArgumentException e) {
            System.out.println("时间输入错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
        }
    }
}
