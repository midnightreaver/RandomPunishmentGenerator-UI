package h2;

import list.data.Record;
import java.sql.*;
import java.util.ArrayList;

/** Handles disk-persisted embedded database.
 * Database file stored in user's home directory
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class H2Handler {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/punishments";

    // Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection connection;
    Statement statement;

    public H2Handler() {
        connection = null;
        statement = null;
    }

    /** Checks if database exists during initial application launch
     * Makes use of H2's <i>IFEXISTS=TRUE</i> connection parameter.
     * If database file already exists, proceeds normally. If not,
     * throws an exception handler
     * @return <b>true</b> if database file exists, <b>false</b> if not
     */
    public boolean dbExists() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/punishments;IFEXISTS=TRUE", USER, PASS);
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    /** Initializes and prepares skeleton for the database
     *
     */
    public void init() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute query to create table
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS punishments (id INTEGER not NULL AUTO_INCREMENT, level INTEGER not NULL, text VARCHAR(255) not NULL, PRIMARY KEY (id))";

            String sql2 = "CREATE TABLE IF NOT EXISTS config (id INTEGER not NULL AUTO_INCREMENT, param VARCHAR(255) NOT NULL, val VARCHAR(255), PRIMARY KEY (id))";

            String sql3 = "INSERT INTO config (param) VALUES ('set_level');";
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing we can do
            }

            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        }
    }

    /** Gets the current highest level from the config table
     *
     * @return an integer representing the current max level
     */
    public int getSavedLevel() {
        connection = null;
        statement = null;

        int val = 0;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "SELECT val FROM config WHERE param = 'set_level'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                val = rs.getInt("val");
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing to be done
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

        return val;
    }

    /** Stores a new highest level in the config table
     *
     * @param level the punishment level
     */
    public void saveLevel(int level) {
        connection = null;
        statement = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            statement = connection.createStatement();
            String sql = "UPDATE config SET val = " + level + " WHERE param = 'set_level'";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    /** Stores a new punishment in the punishments table
     *
     * @param lvl the punishment level
     * @param txt the punishment description
     */
    public void addRecord(int lvl, String txt) {
        connection = null;
        statement = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            statement = connection.createStatement();
            String escapedString = txt.replace("'","''"); // escape single quote to prevent query break
            String sql = "INSERT INTO punishments (level, text) VALUES (" + lvl + ", '" + escapedString +"')";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    /** Deletes a punishment from the punishment table
     *
     * @param recordId ID number of the punishment to be removed
     */
    public void deleteRecord(int recordId) {
        connection = null;
        statement = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "DELETE FROM punishments WHERE id = " + recordId;
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing can be done
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }

    /** Gets a random Record object based on level
     *
     * @param lvl the specified level of punishments to query
     * @return a Record object representing a randomly selected
     *         punishment of the given level
     */
    public Record getRandomRecordByLevel(int lvl) {
        connection = null;
        statement = null;
        Record tempRecord = new Record();
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "SELECT * FROM punishments WHERE level = " + lvl + " ORDER BY RAND() LIMIT 1";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int level = rs.getInt("level");
                String text = rs.getString("text");
                tempRecord.setId(id);
                tempRecord.setLevel(level);
                tempRecord.setText(text);
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing to be done
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

        return tempRecord;
    }

    /** Gets a list of Record objects based on level
     *
     * @param lvl the level of punishments to be queried
     * @return an ArrayList of Record objects by specified level
     */
    public ArrayList<Record> getRecordsByLevel(int lvl) {
        connection = null;
        statement = null;
        ArrayList<Record> tempRecords = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "SELECT * FROM punishments WHERE level = " + lvl;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int level = rs.getInt("level");
                String text = rs.getString("text");
                Record tmpRecord = new Record(id, level, text);
                tempRecords.add(tmpRecord);
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing to be done
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

        return tempRecords;
    }

    /** Gets a list of all Records in the punishments table
     *
     * @return an ArrayList of all Record objects in the
     *         punishments table
     */
    public ArrayList<Record> getAllRecords() {
        connection = null;
        statement = null;
        ArrayList<Record> tempRecords = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "SELECT * FROM punishments ORDER BY level ASC, id ASC";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int level = rs.getInt("level");
                String text = rs.getString("text");
                Record tmpRecord = new Record(id, level, text);
                tempRecords.add(tmpRecord);
            }
            rs.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                // nothing to be done
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

        return tempRecords;
    }
}
