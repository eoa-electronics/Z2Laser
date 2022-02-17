//import libraries
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class AppUI {
    //attributes
    private boolean isVisible;
    private boolean reloadEnabled;
    private File gcodeFile;

    //global UI components
    private JFrame window;
    private JTextArea _TaLog;

    //constructor
    public AppUI() {
        //initialize attributes
        isVisible = false;
        reloadEnabled = false;

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
        JTextArea _TaEditor = new JTextArea(20, 50);
        JScrollPane _SPEditor = new JScrollPane(_TaEditor);
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
        _TaLog = new JTextArea(5, 50);
        _TaLog.setEditable(false);
        JScrollPane _SPLog = new JScrollPane(_TaLog);
        _SPLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        _SPLog.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lm.gridy = 0;
        lm.gridwidth = 1;
        addObject(_SPLog, lm, _PanelLog, window);

        //show window
        setVisibility(true, window);
        reloadEnabled = true;
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

    public void updateLog(String log) {
        _TaLog.setText(log);
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
}