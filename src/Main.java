import h2.H2Handler;
import main.MainMenu;
import start.Start;

/** Entry point of application
 * <i>if (h2.dbExists)</i> checks if the
 * database file already exists. If it exists,
 * proceed to Main Menu, otherwise proceed to Start class
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {

        H2Handler h2 = new H2Handler();

        if (h2.dbExists()) { // DB already created
            new MainMenu();
        } else {
            new Start();
        }
    }
}