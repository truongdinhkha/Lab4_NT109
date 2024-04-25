import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Log {
    public static void log(Object obj) {

        String logFilePath = "log.txt";


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);

        String logContent = timestamp + " - " + obj.toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logContent);
            writer.newLine();
            System.out.println("Đã ghi log thành công: " + obj.toString());
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi log: " + e.getMessage());
        }
        
    }
}
