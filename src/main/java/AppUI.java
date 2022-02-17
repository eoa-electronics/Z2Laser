//import libraries
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;

public class AppUI {
    //attributes
    private boolean isVisible;
    private boolean reloadEnabled;
    private LogManager logmanager;

    //global UI components
    private JFrame window;
    private JTextArea _TaLog;
    private JLabel Status;

    //constructor
    public AppUI(LogManager _logmanager) {
        //initialize attributes
        isVisible = false;
        reloadEnabled = false;
        logmanager = _logmanager;

        //colors
        Color colorBackground = new Color(179, 179, 179);
        Color colorButtonBG = new Color(7, 144, 175);
        Color colorButtonText = new Color(255, 255, 255);

        //create window
        window = new JFrame("Z2Laser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 1000);

        //get content pane
        Container pane = window.getContentPane();
        pane.setLayout(new GridBagLayout());

        //initialize layout manager
        GridBagConstraints lm = new GridBagConstraints();
        lm.fill = GridBagConstraints.HORIZONTAL;
        lm.ipadx = 15;
        lm.ipady = 10;
        lm.insets = new Insets(5, 5, 5, 5);
        lm.anchor = GridBagConstraints.NORTH;
        lm.weighty = 1;
        lm.gridx = 0;
        lm.gridy = 0;

        //apply design
        pane.setBackground(colorBackground);

        //BUILD UI
        //headline
        JLabel _headline = new JLabel("Z2Laser");
        _headline.setFont(new Font(_headline.getFont().getName(), Font.BOLD, 50));
        _headline.setHorizontalAlignment(SwingConstants.CENTER);
        lm.gridwidth = 2;
        addObject(_headline, lm, pane, window);
        lm.gridwidth = 1;

        //open, save, convert buttons inside border
        JPanel _PanelFile = new JPanel(new GridBagLayout());
        _PanelFile.setBackground(colorBackground);
        _PanelFile.setBorder(new TitledBorder(new EtchedBorder(), "File"));
        lm.gridy = 1;
        addObject(_PanelFile, lm, pane, window);
        JButton _BtOpen = new JButton("Open");
        applyDesignButton(_BtOpen, colorButtonBG, colorButtonText);
        lm.gridy = 0;
        addObject(_BtOpen, lm, _PanelFile, window);
        JButton _BtSave = new JButton("Save");
        applyDesignButton(_BtSave, colorButtonBG, colorButtonText);
        lm.gridy = 1;
        addObject(_BtSave, lm, _PanelFile, window);
        JButton _BtConvert = new JButton("Convert");
        applyDesignButton(_BtConvert, colorButtonBG, colorButtonText);
        lm.gridy = 2;
        addObject(_BtConvert, lm, _PanelFile, window);

        //settings inside border
        JPanel _PanelSettings = new JPanel(new GridBagLayout());
        _PanelSettings.setBackground(colorBackground);
        _PanelSettings.setBorder(new TitledBorder(new EtchedBorder(), "Settings"));
        lm.gridx = 1;
        lm.gridy = 1;
        addObject(_PanelSettings, lm, pane, window);
        lm.gridx = 0;
        lm.gridy = 0;
        addObject(new JLabel("G-Code for Z-Up (Laser Off):"), lm, _PanelSettings, window);
        lm.gridy = 1;
        addObject(new JLabel("G-Code for Z-Down (Laser On):"), lm, _PanelSettings, window);
        lm.gridy = 2;
        addObject(new JLabel("Delete all existing M3 / M5 Commands"), lm, _PanelSettings, window);
        lm.gridy = 3;
        addObject(new JLabel("Laser On Power (S-Value)"), lm, _PanelSettings, window);
        lm.gridy = 4;
        addObject(new JLabel("Add M5 at beginning / end (end is really recommended)"), lm, _PanelSettings, window);
        JButton _BtLoadDefaults = new JButton("Load Defaults");
        applyDesignButton(_BtLoadDefaults, colorButtonBG, colorButtonText);
        lm.gridy = 5;
        addObject(_BtLoadDefaults, lm, _PanelSettings, window);
        JButton _BtSaveDefaults = new JButton("Save as Defaults");
        applyDesignButton(_BtSaveDefaults, colorButtonBG, colorButtonText);
        lm.gridx = 1;
        lm.gridwidth = 2;
        addObject(_BtSaveDefaults, lm, _PanelSettings, window);
        JTextField _TfGcodeUp = new JTextField();
        lm.gridy = 0;
        addObject(_TfGcodeUp, lm, _PanelSettings, window);
        JTextField _TfGcodeDown = new JTextField();
        lm.gridy = 1;
        addObject(_TfGcodeDown, lm, _PanelSettings, window);
        JSpinner _SpLaserPower = new JSpinner();
        lm.gridy = 3;
        addObject(_SpLaserPower, lm, _PanelSettings, window);
        lm.gridwidth = 1;
        JCheckBox _CbDeleteM3 = new JCheckBox("M3");
        lm.gridy = 2;
        addObject(_CbDeleteM3, lm, _PanelSettings, window);
        JCheckBox _CbDeleteM5 = new JCheckBox("M5");
        lm.gridx = 2;
        addObject(_CbDeleteM5, lm, _PanelSettings, window);
        JCheckBox _CbAddM3 = new JCheckBox("M3");
        lm.gridx = 1;
        lm.gridy = 4;
        addObject(_CbAddM3, lm, _PanelSettings, window);
        JCheckBox _CbAddM5 = new JCheckBox("M5");
        lm.gridx = 2;
        addObject(_CbAddM5, lm, _PanelSettings, window);

        //G-Code editor inside border
        JPanel _PanelEditor = new JPanel(new GridBagLayout());
        _PanelEditor.setBackground(colorBackground);
        _PanelEditor.setBorder(new TitledBorder(new EtchedBorder(), "G-Code Editor"));
        lm.gridx = 0;
        lm.gridy = 2;
        lm.gridwidth = 2;
        addObject(_PanelEditor, lm, pane, window);
        JTextArea Editor = new JTextArea(18, 60);
        JScrollPane _SPEditor = new JScrollPane(Editor);
        _SPEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        _SPEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lm.gridy = 0;
        lm.gridwidth = 1;
        addObject(_SPEditor, lm, _PanelEditor, window);

        //Log viewer in border
        JPanel _PanelLog = new JPanel(new GridBagLayout());
        _PanelLog.setBackground(colorBackground);
        _PanelLog.setBorder(new TitledBorder(new EtchedBorder(), "Log"));
        lm.gridx = 0;
        lm.gridy = 3;
        lm.gridwidth = 2;
        addObject(_PanelLog, lm, pane, window);
        _TaLog = new JTextArea(5, 60);
        _TaLog.setEditable(false);
        JScrollPane _SPLog = new JScrollPane(_TaLog);
        _SPLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        _SPLog.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lm.gridy = 0;
        lm.gridwidth = 1;
        addObject(_SPLog, lm, _PanelLog, window);
        _TaLog.setText(logmanager.printLog());

        //Status bar
        lm.ipadx = 0;
        lm.ipady = 0;
        lm.gridx = 0;
        lm.gridy = 4;
        lm.gridwidth = 2;
        addObject(new JSeparator(), lm, pane, window);
        Status = new JLabel("Status:  Initializing... Please wait.");
        lm.gridy = 5;
        lm.ipady = 3;
        addObject(Status, lm, pane, window);


        //BUTTON ACTIONS
        //open file button
        _BtOpen.addActionListener(a ->
                {
                    addLog("Open File... Please select a G-Code file.");
                    setStatus("Open... Please select a G-Code file.");
                    FileManager fileman = new FileManager(window);
                    try{
                        File gcodeFile = fileman.selectorOpen();
                        addLog("Selected file '" + gcodeFile.toString() + "' successfully.");
                        addLog("Opening file. Please wait...");
                        setStatus("Opening... Please wait.");
                        FileHandler filehandler = new FileHandler(gcodeFile);
                        Editor.setText(filehandler.readText());
                        addLog("Opened file successfully.");
                    } catch(Exception ex) {
                        addLog("Error trying to open File: " + ex);
                    }
                    setStatus("Idle");
                });
        //save file button
        _BtSave.addActionListener(a ->
                {
                    addLog("Save File... Please select destination and name.");
                    setStatus("Save... Please select destination and name.");
                    FileManager fileman = new FileManager(window);
                    try{
                        File gcodeFile = fileman.selectorSave("nc", "NC G-Code");
                        addLog("Selected destination '" + gcodeFile.toString() + "' successfully.");
                        addLog("Saving to '" + gcodeFile.toString() + "'. Please wait...");
                        setStatus("Saving... Please wait.");
                        FileHandler filehandler = new FileHandler(gcodeFile);
                        filehandler.writeText(Editor.getText());
                        addLog("Saved to '" + gcodeFile.toString() + "' successfully.");
                    } catch(Exception ex) {
                        addLog("Error trying to save File: " + ex);
                    }
                    setStatus("Idle");
                });
        //convert button



        //show window
        setVisibility(true, window);
        setStatus("Idle");
    }

    //methods

    //set window visibility
    private void setVisibility(boolean visible, JFrame _window) {
        _window.setVisible(visible);
        isVisible = visible;
    }

    private void reload(boolean forceReload ,JFrame _window) {
        if((reloadEnabled && isVisible) || forceReload) {
            setVisibility(false, _window);
            setVisibility(true, _window);
        }
    }

    public void update() {
        _TaLog.setText(logmanager.printLog());
        reload(false, window);
    }

    private void addObject(JComponent object, GridBagConstraints lm, Container pane, JFrame window) {
        pane.add(object, lm);
        reload(false, window);
    }
    private void addObject(JComponent object, GridBagConstraints lm, JPanel pane, JFrame window) {
        pane.add(object, lm);
        reload(false, window);
    }

    private void applyDesignButton(JButton button, Color background, Color text) {
        button.setBackground(background);
        button.setForeground(text);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    public void addLog(String entryText) {
        logmanager.addEntry(entryText);
        update();
    }

    private void setStatus(String status) {
        Status.setText("Status:  " + status);
        reload(false, window);
    }
}