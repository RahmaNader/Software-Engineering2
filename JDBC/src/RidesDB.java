import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class RidesDB {
    private static Statement stmt;
    private static Connection connection;
    private static RidesDB uniqueInstance;
    private static final LocationDB locationDB = LocationDB.getInstance();
    Scanner input = new Scanner(System.in);
    String in;

    private static void setupDbConnection(){
        try{
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
        }catch (Exception e){
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

    private RidesDB() {
        setupDbConnection();
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            stmt = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS RIDES"+
                         "(ID        INTEGER    PRIMARY KEY      AUTOINCREMENT,"+
                         "SOURCE           CHAR(50)    NOT NULL,"+
                         "DESTINATION            CHAR(50)     NOT NULL,"+
                         "STATUS      TEXT CHECK( STATUS IN ('A','D','P') )  NOT NULL DEFAULT 'P',"+
                         "PRICE            FLOAT(2)     DEFAULT 0,"+
                         "DRIVER            CHAR(50)     ,"+
                         "USER            CHAR(50)     NOT NULL ,"+
                         "FOREIGN KEY (DRIVER) REFERENCES DRIVER (USERNAME),"+
                         "FOREIGN KEY (USER) REFERENCES USER (USERNAME));";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            closeConnection();
            System.out.println("Opened Rides successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static RidesDB getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new RidesDB();
        }
        return uniqueInstance;
    }

    public void loadDB() {
        setupDbConnection();
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            System.out.println("Opened rides successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE STATUS != 'A' OR STATUS !='D';");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
                System.out.println("SOURCE = " + rs.getString("source"));
                System.out.println("DESTINATION = " + rs.getString("destination"));
                System.out.println("DRIVER = " + rs.getString("driver"));
                System.out.println("USER = " + rs.getString("user"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("////////////////////");
            }
            closeConnection();
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //user method
    public void requestRide(Person p) {
        setupDbConnection();
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            Rides ride = new Rides();
            System.out.println("Source: ");
            in = input.nextLine();
            ride.setSource(in);
            System.out.println("Destination: ");
            in = input.nextLine();
            ride.setDestination(in);
            ride.setUser(p.getUserName());
            String sql = "INSERT INTO RIDES (SOURCE,DESTINATION,USER) " +
                    "VALUES ("+ "'" + ride.getSource() + "'" + "," + "'" + ride.getDestination() + "'" + "," + "'" + ride.getUser() + "'" + ");";
            stmt.executeUpdate(sql);
            connection.commit();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }

    //user method
    public void viewRequests(Person p){
        setupDbConnection();
        int in;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            System.out.println("Opened rides successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE USER= '"+p.getUserName() +"' AND STATUS = 'P';");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
                System.out.println("SOURCE = " + rs.getString("source"));
                System.out.println("DESTINATION = " + rs.getString("destination"));
                System.out.println("DRIVER = " + rs.getString("driver"));
                System.out.println("PRICE = " + rs.getString("price"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("////////////////////");
            }
            rs.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("1- Accept offer?"+"\n"+"2- Deny offer?"+"\n"+"3- Go back");
        in = input.nextInt();
        if(in == 1) this.acceptRequest();
        else if(in == 2) this.refuseRequest(p);
    }

    //user method
    public void acceptRequest(){
        setupDbConnection();
        System.out.println("Please enter request id");
        int in = input.nextInt();
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            connection.setAutoCommit(false);
            System.out.println("Opened rides successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+in+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
            }
            String sql = "UPDATE RIDES set STATUS = 'A' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            connection.commit();
            rs.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //user method
    public void refuseRequest(Person P){

    }

    //driver method
    public void makeOffer(Person p){
        setupDbConnection();
        System.out.println("Please enter ride id");
        int in = input.nextInt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+in+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
                System.out.println("SOURCE = "+rs.getString("source"));
                System.out.println("DESTINATION = "+rs.getString("destination"));
            }
            stmt = connection.createStatement();
            System.out.println("Enter a suitable price:");
            int price = input.nextInt();
            String sql = "UPDATE RIDES set PRICE = "+price+" WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            connection.commit();
            sql = "UPDATE RIDES set DRIVER = '"+p.getUserName()+"' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            connection.commit();
            rs.close();
            closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //driver method
    public void displayFavorite(Person p){

    }
}
