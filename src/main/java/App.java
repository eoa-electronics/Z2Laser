public class App {
    public static void main(String args[]) {
        AppUI appui = new AppUI();
        LogManager logmanager = new LogManager(appui);
        logmanager.addEntry("Loading...");
        logmanager.addEntry("Ready.");
    }
}
