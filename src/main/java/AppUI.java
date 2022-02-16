//import libraries
import javax.swing.*;
import java.awt.*;

public class AppUI {
    //attributes
    private boolean isVisible;
    private boolean reloadEnabled;

    //constructor
    public AppUI() {
        //initialize attributes
        isVisible = false;
        reloadEnabled = false;

        //colors
        Color colorBackground = new Color(179, 179, 179);

        //create window
        JFrame window = new JFrame("Z2Laser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);

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
        addObject(_headline, lm, pane, window);


        //show window
        setVisibility(true, window);
    }

    //methods

    //set window visibility
    private void setVisibility(boolean visible, JFrame window) {
        window.setVisible(visible);
        isVisible = visible;
    }

    private void reload(boolean forceReload ,JFrame window) {
        if((reloadEnabled && isVisible) || forceReload) {
            setVisibility(false, window);
            setVisibility(true, window);
        }
    }

    private void addObject(JComponent object, GridBagConstraints lm, Container pane, JFrame window) {
        pane.add(object, lm);
        reload(false, window);
    }
}
