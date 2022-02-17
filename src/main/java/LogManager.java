import java.util.ArrayList;
import java.util.List;

public class LogManager {
    //attributes
    private List<LogEntry> log;
    public boolean logUpdated;

    //constructor
    public LogManager() {
        logUpdated = false;
        log = new ArrayList<LogEntry>();
    }

    public void addEntry(String entryText) {
        log.add(new LogEntry(entryText));
        logUpdated = true;
    }

    public String printLog() {
        String textOut = "";
        for(LogEntry entry : log) {
            textOut = textOut.concat(entry.print() + "\n");
        }
        return(textOut);
    }

    public boolean isLogUpdated() {
        boolean _temp = logUpdated;
        logUpdated = false;
        return _temp;
    }
}
