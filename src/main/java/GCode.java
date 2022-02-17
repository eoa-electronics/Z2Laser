public class GCode {
    //attributes
    private String gcode;

    //enums
    public enum POSITION {
        BEGINNING,
        END
    }

    //constructor
    public GCode(String GCode) {
        gcode = GCode;
    }

    //methods

    public String getGcode() {
        return gcode;
    }

    public long getLines() {
        return gcode.split("\n").length;
    }

    public void replaceAll(String toReplace, String replaceWith) {
        gcode = gcode.replaceAll(toReplace, replaceWith);
    }

    public void deleteAll(String toDelete) {
        gcode = gcode.replaceAll(toDelete, "");
    }

    public void add(String code, POSITION position) {
        if(position == POSITION.BEGINNING) {
            gcode = "M5\n" + gcode;
        } else if(position == POSITION.END) {
            gcode = gcode + "\nM5\n";
        }
    }

    public void removeLineBeginningWith(String beginning) {
        int indexBeginning = gcode.indexOf(beginning);
        int indexLineEnd = gcode.indexOf("\n", indexBeginning);
        gcode = gcode.replaceAll(gcode.substring(indexBeginning, (indexLineEnd + 1)), "");
    }
}
