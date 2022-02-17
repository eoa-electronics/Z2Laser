import java.util.ArrayList;
import java.util.List;

public class LogManager {
    //attributes
    private List<LogEntry> log;

    //constructor
    public LogManager() {
        log = new ArrayList<LogEntry>();
    }

    public void addEntry(String entryText) {
        log.add(new LogEntry(entryText));
    }

    public String printLog() {
        String textOut = "";
        for(LogEntry entry : log) {
            textOut = textOut.concat(entry.print() + "\n");
        }
        return(textOut);
    }
}
