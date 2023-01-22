package list.data;

/** Custom data class for management of records/punishments
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class Record {

    private int id;
    private int level;
    private String text;

    public Record() {

    }

    public Record(String text) {
        this.level = 1;
        this.text = text;
    }

    public Record(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public Record(int id, int level, String text) {
        this.id = id;
        this.level = level;
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int lvl) {
        this.level = lvl;
    }

    public void setText(String txt) {
        this.text = txt;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }
}
