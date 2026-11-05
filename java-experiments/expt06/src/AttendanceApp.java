import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class AttendanceApp {
    public static void main(String[] args) {
        LocalTime deadline = LocalTime.of(9, 0, 0); // 早上 9 点。
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        try {
            // 不填参数就使用当前北京时间；填参数可以练习不同情况。
            if (args.length > 0) now = LocalTime.parse(args[0]);
            System.out.println("打卡时间：" + now);
            if (now.isAfter(deadline)) {
                throw new MyException("超过早上 9 点，打卡失败！");
            }
            System.out.println("打卡成功！");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("时间格式错误，请填写类似 08:59:59 的时间。");
        }
    }
}
