import org.json.JSONObject;

import java.io.File;

public class SettingsPersistent {
    private JSONObject settings;
    private boolean isLoaded;
    private AppUI appui;
    private boolean blocked;

    public SettingsPersistent(AppUI ui) {
        settings = new JSONObject();
        appui = ui;
        isLoaded = false;
        blocked = false;
    }

    private void applyDefaults() {
        appui.addLog("WARNING: Applying software-defaults for all settings!");
        settings.put("GcodeUp", "G1 Z0 F100");
        settings.put("GcodeDown", "G1 Z-1 F100");
        settings.put("LaserPower", 0);
        settings.put("DeleteAllM3", true);
        settings.put("DeleteAllM5", false);
        settings.put("AddM5Beginning", false);
        settings.put("AddM5End", true);
        isLoaded = true;
    }

    public void createSettingsFile(boolean defaults) {
        appui.addLog("Creating new settings file... Please wait.");
        File settingsFile = new File("./settings.json");
        try{
            settingsFile.createNewFile();
            if(defaults) {
                applyDefaults();
            }
            appui.addLog("Created new settings file successfully.");
            saveSettings();
        } catch (Exception ex) {
            blocked = true;
            appui.addLog("Unable to create settings file. Please check permission.");
            System.out.println("Error:  " + ex);
        }

    }

    public void writeSettings(String zUp, String zDown, int pwr, boolean delM3, boolean delM5, boolean addM5B, boolean addM5E) {
        settings.put("GcodeUp", zUp);
        settings.put("GcodeDown", zDown);
        settings.put("LaserPower", pwr);
        settings.put("DeleteAllM3", delM3);
        settings.put("DeleteAllM5", delM5);
        settings.put("AddM5Beginning", addM5B);
        settings.put("AddM5End", addM5E);
        isLoaded = true;
    }

    public String getZUp() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getString("GcodeUp"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'G-Code for Z-Up (Laser Off)'. Applying software-default!");
            return("");
        }

    }
    public String getZDown() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getString("GcodeDown"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'G-Code for Z-Up (Laser On)'. Applying software-default!");
            return("");
        }
    }
    public int getPwr() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getInt("LaserPower"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'Laser-On Power (S-Value)'. Applying software-default!");
            return(0);
        }
    }
    public boolean getDelM3() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getBoolean("DeleteAllM3"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'Delete all existing M3 commands before processing'. Applying software-default!");
            return(true);
        }
    }
    public boolean getDelM5() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getBoolean("DeleteAllM5"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'Delete all existing M5 commands before processing'. Applying software-default!");
            return(false);
        }
    }
    public boolean getAddM5B() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getBoolean("AddM5Beginning"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'Add M5 at file beginning after processing'. Applying software-default!");
            return(false);
        }
    }
    public boolean getAddM5E() {
        if(!isLoaded) {
            loadSettings();
        }
        try{
            return(settings.getBoolean("AddM5End"));
        } catch (Exception ex) {
            appui.addLog("WARNING: Could not load 'Add M5 at file end after processing'. Applying software-default!");
            return(true);
        }
    }

    public void loadSettings() {
        if(!blocked) {
            appui.addLog("Loading settings from file... Please wait.");
            File settingsFile = new File("./settings.json");
            FileHandler filehandler = new FileHandler(settingsFile);
            String data = "";
            try {
                data = filehandler.readText();
                settings = new JSONObject(data);
                isLoaded = true;
                appui.addLog("Loaded settings successfully.");
            } catch (Exception ex) {
                appui.addLog("Settings file not found or unable to open.");
                createSettingsFile(true);
            }
        }
    }

    public void saveSettings() {
        if(!blocked) {
            appui.addLog("Saving settings to file... Please wait.");
            if (!(isLoaded)) {
                applyDefaults();
            }
            File settingsFile = new File("./settings.json");
            FileHandler filehandler = new FileHandler(settingsFile);
            try {
                filehandler.writeText(settings.toString());
                appui.addLog("Saved settings successfully");
            } catch (Exception ex) {
                appui.addLog("Settings file not found or unable to save.");
                createSettingsFile(false);
            }
        }
    }
}
