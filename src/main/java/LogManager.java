import java.util.ArrayList;
import java.util.List;

public class LogManager {
    //attributes
    private List<LogEntry> log;
    private AppUI ui;

    //constructor
    public LogManager(AppUI appui) {
        log = new ArrayList<LogEntry>();
        ui = appui;
    }

    private void updateAppUI() {
        ui.updateLog(printLog());
    }

    public void addEntry(String entryText) {
        log.add(new LogEntry(entryText));
        updateAppUI();
    }

    public String printLog() {
        String textOut = "";
        for(LogEntry entry : log) {
            textOut = textOut.concat(entry.print() + "\n");
        }
        return(textOut);
    }
}
