import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

public class Database {
    private static Statement stmt;
    private static Connection connection;
    private static void setupDbConnection(){
        try{
            Class.forName("Database");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            stmt = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void createRideDB(){
        try {
            Class.forName("Database");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            stmt = connection.createStatement();
            String sql = """
                    CREATE TABLE IF NOT EXISTS RIDES
                         (ID        INTEGER    PRIMARY KEY      AUTOINCREMENT,
                         SOURCE           CHAR(50)    NOT NULL,
                         DESTINATION            CHAR(50)     NOT NULL,
                         DRIVER            CHAR(50)     ,
                          FOREIGN KEY (DRIVER) REFERENCES DRIVER (USERNAME),
                         USER            CHAR(50)     NOT NULL ,
                         FOREIGN KEY (USER) REFERENCES USER (USERNAME),
                         PRICE            FLOAT(2)     DEFAULT 0 ,
                         STATUS      TEXT CHECK( STATUS IN ('A','D','P') )   NOT NULL DEFAULT 'P');""";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened Rides successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void createUserDriverDB() {
        try {
            String sql = """
                    CREATE TABLE IF NOT EXISTS USER
                        (USERNAME CHAR(50) PRIMARY KEY     NOT NULL,
                        NAME           TEXT    NOT NULL,
                        MOBILE            CHAR(20)     NOT NULL,
                        STATUS      TEXT CHECK( STATUS IN ('S','A') )   NOT NULL DEFAULT 'A',
                        EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ,
                        PASSWORD        CHAR(30)     NOT NULL);""";
            stmt.executeUpdate(sql);
            sql = """
                    CREATE TABLE IF NOT EXISTS DRIVER
                        (USERNAME CHAR(50) PRIMARY KEY     NOT NULL,
                        NAME           TEXT    NOT NULL,
                        MOBILE            CHAR(20)     NOT NULL,
                        STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'P',
                        EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ,
                        NATIONALID      CHAR(30) NOT NULL ,
                        LICENSE      CHAR(30) NOT NULL ,
                        PASSWORD        CHAR(30)     NOT NULL);""";
            stmt.executeUpdate(sql);
            System.out.println("Opened persons successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createLocationsDB(){
        try {
            String sql = """
                    CREATE TABLE IF NOT EXISTS LOCATIONS
                        (ID     INTEGER    PRIMARY KEY      AUTOINCREMENT,
                        USERNAME   CHAR(50)  NOT NULL,
                        LOCATION    CHAR(100) NOT NULL,
                        FOREIGN KEY (USERNAME) REFERENCES DRIVER (USERNAME));""";
            stmt.executeUpdate(sql);
            System.out.println("Opened locations successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Database(){
        setupDbConnection();
        createUserDriverDB();
        createRideDB();
        createLocationsDB();
    }
    public static void main(String [] args){
        Database db = new Database();
    }
}
