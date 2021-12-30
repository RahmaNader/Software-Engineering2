import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Statement stmt;
    private static Database databaseInstance;

    private static void createRideDB(){
        try{
            String sql =
                    "CREATE TABLE IF NOT EXISTS RIDES"+
                            "(ID        INTEGER    PRIMARY KEY      AUTOINCREMENT,"+
                            "SOURCE           CHAR(50)    NOT NULL,"+
                            "DESTINATION            CHAR(50)     NOT NULL,"+
                            "STATUS      TEXT CHECK( STATUS IN ('A','D','P') )  NOT NULL DEFAULT 'P',"+
                            "PRICE            FLOAT(2)     DEFAULT 0,"+
                            "DRIVER            CHAR(50)     ,"+
                            "USER            CHAR(50)     NOT NULL ," +
                            "Date         Date   NOT NULL," +
                            "NUMOFPASSENGERS   INTEGER   NOT NULL,"+
                            "FOREIGN KEY (DRIVER) REFERENCES DRIVER (USERNAME),"+
                            "FOREIGN KEY (USER) REFERENCES USER (USERNAME));";
            stmt.executeUpdate(sql);
            System.out.println("Opened Rides successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void createUserDB() {
        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS USER"+
                            "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL,"+
                            "NAME           TEXT    NOT NULL,"+
                            "MOBILE            CHAR(20)     NOT NULL,"+
                            "STATUS      TEXT CHECK( STATUS IN ('S','A') )   NOT NULL DEFAULT 'A',"+
                            "EMAIL      CHAR(30) DEFAULT 'null' ,"+
                            "PASSWORD        CHAR(30)     NOT NULL,"+
                            "BIRTHDATE      CHAR(30)      NOT NULL);";
            stmt.executeUpdate(sql);
            System.out.println("Opened users successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createDriverDB() {
        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS DRIVER"+
                            "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL,"+
                            "NAME           TEXT    NOT NULL,"+
                            "MOBILE            CHAR(20)     NOT NULL,"+
                            "STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'P',"+
                            "EMAIL      CHAR(30) DEFAULT 'null' ,"+
                            "NATIONALID      CHAR(30) NOT NULL ,"+
                            "LICENSE      CHAR(30) NOT NULL ,"+
                            "PASSWORD        CHAR(30)     NOT NULL);";
            stmt.executeUpdate(sql);
            System.out.println("Opened drivers successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createLocationsDB(){
        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS LOCATIONS"+
                            "(ID     INTEGER    PRIMARY KEY      AUTOINCREMENT,"+
                            "USERNAME   CHAR(50)  NOT NULL,"+
                            "LOCATION    CHAR(100) NOT NULL,"+
                            "FOREIGN KEY (USERNAME) REFERENCES DRIVER (USERNAME));";
            stmt.executeUpdate(sql);
            System.out.println("Opened locations successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createRatingDB(){
        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS RATING" +
                            "(ID        INTEGER    PRIMARY KEY      AUTOINCREMENT," +
                            "RATE DOUBLE NOT NULL DEFAULT 0," +
                            "USER    CHAR(50)  NOT NULL," +
                            "DRIVER CHAR(50) NOT NULL," +
                            "FOREIGN KEY (USER) REFERENCES USER (USERNAME)," +
                            "FOREIGN KEY (DRIVER) REFERENCES DRIVER (USERNAME) );";
            stmt.executeUpdate(sql);
            System.out.println("Opened rating successfully");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createNotificationDB(){
        try{
            String sql =
                    "CREATE TABLE IF NOT EXISTS NOTIFICATION"+
                            "(SOURCE           CHAR(50)    NOT NULL,"+
                            "DESTINATION            CHAR(50)     NOT NULL,"+
                            "SEEN      TEXT CHECK( SEEN IN ('Y','N') )  NOT NULL DEFAULT 'N',"+
                            "DRIVER            CHAR(50)," +
                            "UNIQUE (DRIVER,SOURCE,DESTINATION));";
            stmt.executeUpdate(sql);
            System.out.println("Opened Rides successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createEventDB ( ) {

        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS EVENT" +
                            "(ID        INTEGER    PRIMARY KEY      AUTOINCREMENT," +
                            "RIDEID INTEGER NOT NULL," +
                            "EVENTNAME    CHAR(50) NOT NULL UNIQUE," +
                            "DRIVER  CHAR (50) NOT NULL," +
                            "USER    CHAR (50) NOT NULL," +
                            "TIME    CHAR (50) NOT NULL," +
                            "PRICE   DOUBLE NOT NULL," +
                            "FOREIGN KEY (RIDEID) REFERENCES RIDES (ID));";
            stmt.executeUpdate ( sql );
        }
        catch (SQLException e) {
            e.printStackTrace ( );
        }
        System.out.println("Opened Events successfully");


    }

    private static void createDiscountsDB (){
        String sql = "CREATE TABLE IF NOT EXISTS DISCOUNTS" +
                     "(TIMES  DATE  NOT NULL," +
                        "AREA  CHAR(50) NOT NULL," +
                            "DISCOUNT  DOUBLE NOT NULL " +
                        ");";

        try {
            stmt.executeUpdate ( sql );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }

        System.out.println ("Opened Discounts successfully!" );

    }


    private Database(){
        
    }

    public static Database getInstance(){
        if(databaseInstance == null){
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    public void start(){
        DBConnection.setupDbConnection("Database");
        stmt = DBConnection.getStmt();
        createUserDB();
        createDriverDB();
        createRideDB();
        createLocationsDB();
        createRatingDB();
        createNotificationDB();
        createEventDB();
        createDiscountsDB ();
    }



}
