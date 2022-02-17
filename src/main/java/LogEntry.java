import java.sql.Timestamp;

public class LogEntry {
    //attributes
    public Timestamp time;
    public String entry;

    //constructor
    public LogEntry(String entryText) {
        time = new Timestamp(System.currentTimeMillis());
        entry = entryText;
    }

    public String print() {
        String logText = (time.toString() + ": " + entry);
        return(logText);
    }
}
