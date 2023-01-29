import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {

    private String ipAddress;
    private String userName;
    private LocalDateTime dateTime;
    private  String request;
    private  String response;
    private  String bytesSent;
    private  String referer;
    private  String userAgent;
    Methods method;
    final String regex = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"";

    public LogEntry(String string) {
        Pattern p = Pattern.compile(this.regex);
        Matcher matcher = p.matcher(string);
        if (matcher.find()) {
            System.out.println("IP Address: " + matcher.group(1));
            this.ipAddress=matcher.group(1);
            System.out.println("UserName: " + matcher.group(3));
            this.userName=matcher.group(3);
            System.out.println("Date/Time: " + matcher.group(4));
            this.dateTime= LocalDateTime.parse(matcher.group(4));
            System.out.println("Request: " + matcher.group(5));
            this.request=matcher.group(5);
            System.out.println("Response: " + matcher.group(6));
            this.response=matcher.group(6);
            System.out.println("Bytes Sent: " + matcher.group(7));
            this.bytesSent=matcher.group(7);
            System.out.println("--------------------------------------------");
            if (!matcher.group(8).equals("-")){
                System.out.println("Referer: " + matcher.group(8));
                this.referer=matcher.group(8);
            }
            System.out.println("User-Agent: " + matcher.group(9));
            this.userAgent=matcher.group(9);
        }

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public String getBytesSent() {
        return bytesSent;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
