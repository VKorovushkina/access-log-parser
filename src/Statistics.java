import java.time.LocalDateTime;
import java.time.Month;

public class Statistics {
    LogEntry logEntry;
    int totalTraffic;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    public Statistics(){
        this.totalTraffic=0;
        this.maxTime=LocalDateTime.of(2020, Month.JANUARY,1,0,0,0);
        this.minTime=LocalDateTime.of(2023, Month.DECEMBER,31,23,59,59);
    }

    public void addEntry(LogEntry logEntry){
        int bytes=Integer.parseInt(logEntry.getBytesSent());
        this.totalTraffic+=bytes;
        if (logEntry.getDateTime().isBefore(this.minTime)){
            this.minTime=logEntry.getDateTime();
        }
        if (logEntry.getDateTime().isBefore(this.maxTime)){
            this.maxTime=logEntry.getDateTime();
        }
    }

    public double getTrafficRate(){
        double help = this.maxTime.getHour()-this.minTime.getHour();
        return this.totalTraffic/help;
    }

}
