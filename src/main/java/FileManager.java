//import libraries
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
    //attributes
    private final  JFrame parentFrame;

    //constructor
    public FileManager(JFrame parent) {
        parentFrame = parent;
    }

    //exceptions
    public static Exception ChooserException = new Exception("No file selected, or unable to open file.");

    //methods

    //open file via file selector
    public File selectorOpen() throws Exception {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(parentFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            return(chooser.getSelectedFile());
        } else {
            throw ChooserException;
        }
    }

    //open file via file selector with extension filtering
    public File selectorOpen(String extension, String extDescription) throws Exception {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extDescription, extension);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(parentFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            return(chooser.getSelectedFile());
        } else {
            throw ChooserException;
        }
    }

    //save file via file selector
    public File selectorSave() throws Exception {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(parentFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            return(chooser.getSelectedFile());
        } else {
            throw ChooserException;
        }
    }

    //save file via file selector with extension filtering
    public File selectorSave(String extension, String extDescription) throws Exception {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extDescription, extension);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        int result = chooser.showSaveDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String name = file.toString();
            if (name.endsWith("." + extension)) {
                return file;
            } else {
                String nameNew = name + "." + extension;
                return (new File(nameNew));
            }
        } else {
            throw ChooserException;
        }
    }
}
