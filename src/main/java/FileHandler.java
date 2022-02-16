//import libraries
import java.io.*;
import java.util.Scanner;

public class FileHandler {
    //attributes
    private final  File file;

    //constructor
    public FileHandler(File textFile) {
        file = textFile;
    }

    //methods

    //text file to string
    public String readText() throws Exception {
        String text = "";
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                text = text.concat(scanner.nextLine() + "\n");
            }
            scanner.close();
            return(text);
        } catch(FileNotFoundException ex) {
            System.out.println("ERROR: File was not found, or not readable!");
            throw ex;
        }
    }

    public void writeText(String text) throws Exception {
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch(IOException ex) {
            System.out.println("ERROR: File was not found, or not writeable!");
            throw ex;
        }
    }
}
