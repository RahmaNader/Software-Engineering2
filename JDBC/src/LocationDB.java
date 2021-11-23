import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.sql.SQLException;

public class LocationDB {
    private static Connection connection;
    private static Statement stmt;
    private static LocationDB uniqueInstance;
    private final Vector<Locations> locations;
    private final Scanner input = new Scanner(System.in);
    String in;

    private static void setupDbConnection(){
        try {
            Class.forName("LocationDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            stmt = connection.createStatement();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LocationDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LocationDB();
        }
        return uniqueInstance;
    }

    private LocationDB() {
        setupDbConnection();
        locations = new Vector<>();
        try {
            String sql =
                    "CREATE TABLE IF NOT EXISTS LOCATIONS"+
                        "(ID     INTEGER    PRIMARY KEY      AUTOINCREMENT,"+
                        "USERNAME   CHAR(50)  NOT NULL,"+
                        "LOCATION    CHAR(100) NOT NULL,"+
                        "FOREIGN KEY (USERNAME) REFERENCES DRIVER (USERNAME));";
            stmt.executeUpdate(sql);
            System.out.println("Opened locations successfully");
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDB() {

    }

    //driver method
    public void addFavourite(Person p) {
        setupDbConnection();
        try {
            Class.forName("LocationDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            System.out.println("Opened LOCATIONS successfully");
            stmt = connection.createStatement();
            System.out.println("Source: ");
            in = input.nextLine();
            String sql = "INSERT INTO LOCATIONS (LOCATION,USERNAME) " +
                        "VALUES (" + "'" + in + "'" + "," + "'" + p.getUserName() + "'" + ");";
            stmt.executeUpdate(sql);
            connection.commit();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Locations created successfully");
    }

    //driver method
    public Vector<String> displayFavourite(Person p) {
        Vector<String> fav = new Vector<>();
        setupDbConnection();
        try {
            connection.setAutoCommit(false);
            System.out.println("Opened locations successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOCATIONS WHERE USERNAME='" + p.getUserName() + "';");
            while (rs.next()) {
                System.out.println();
                fav.add(rs.getString("location"));
                System.out.println("SOURCE = " + rs.getString("location"));
                System.out.println("////////////////////");
            }
            rs.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fav;
    }

    //driver method
    public void isPlaceRequested(Person p) {
        setupDbConnection();
        try {
            connection.setAutoCommit(false);
            System.out.println("Opened locations successfully");
            ResultSet locationrs = stmt.executeQuery("SELECT * FROM LOCATIONS WHERE USERNAME='" + p.getUserName() + "';");
            try{
            while (locationrs.next()) {
                Class.forName("RidesDB");
                Connection connection2 = DriverManager.getConnection("jdbc:sqlite:database");
                connection2.setAutoCommit(false);
                System.out.println("Opened rides successfully");
                Statement stmt2 = connection2.createStatement();
                ResultSet ridesrs = stmt2.executeQuery("SELECT * FROM RIDES WHERE SOURCE= '" + locationrs.getString("location") + "' AND STATUS = 'P';");
                while (ridesrs.next()) {
                    System.out.println("ID = "+ridesrs.getString("id"));
                    System.out.println("USER = "+ridesrs.getString("user"));
                    System.out.println("SOURCE = "+ridesrs.getString("source"));
                    System.out.println("DESTINATION = "+ridesrs.getString("destination"));
                    System.out.println("PRICE = "+ridesrs.getString("price"));
                    System.out.println("///////////////");
                }
                stmt2.close();
                connection2.close();
                ridesrs.close();
                closeConnection();
            }}catch (ClassNotFoundException | SQLException e){e.printStackTrace();}
            locationrs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
