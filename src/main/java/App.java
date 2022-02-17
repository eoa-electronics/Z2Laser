public class App {
    public static void main(String args[]) {
        LogManager logmanager = new LogManager();
        logmanager.addEntry("Loading...");
        AppUI appui = new AppUI(logmanager);
        appui.addLog("Ready.");
    }
}
