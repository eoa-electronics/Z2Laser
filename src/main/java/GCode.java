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
        StringBuilder gcodeBuilder = new StringBuilder(gcode);
        if(position == POSITION.BEGINNING) {
            gcodeBuilder.insert(0, code);
        } else if(position == POSITION.END) {
            gcodeBuilder.insert(gcode.length(), code);
        }
    }
}
