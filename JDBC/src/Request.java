import java.sql.*;
import java.util.Scanner;


public class Request {
    private static Statement stmt;
    private static final Scanner input = new Scanner(System.in);
    static String in;

    public static void requestRide(Person p) {
        Notification notification = new Notification();
        DBConnection.setupDbConnection("Request");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened database successfully");
            Rides ride = new Rides();
            System.out.println("Source: ");
            in = input.nextLine();
            ride.setSource(in);
            System.out.println("Destination: ");
            in = input.nextLine();
            ride.setDestination(in);
            ride.setUser(p.getUserName());
            String sql = "INSERT INTO RIDES (SOURCE,DESTINATION,USER) " +
                    "VALUES ("+ "'" + ride.getSource() + "'" + "," + "'" + ride.getDestination() + "'" + "," + "'" + p.getUserName() + "'" + ");";
            stmt.executeUpdate(sql);
            DBConnection.closeConnection();
            notification.setUpNotification();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }

    //user method
    public static void viewRequests(Person p){
        DBConnection.setupDbConnection("Request");
        stmt = DBConnection.getStmt();
        int in;
        try {
            System.out.println("Opened rides successfully");

            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE USER= '"+p.getUserName() +"' AND STATUS = 'P';");
            while (rs.next()) {
                Print.rideData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //user method
    public static void acceptRequest(){
        DBConnection.setupDbConnection("Request");
        stmt = DBConnection.getStmt();
        System.out.println("Please enter request id");
        int in = input.nextInt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+in+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
            }
            String sql = "UPDATE RIDES set STATUS = 'A' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //user method
    public static void cancelRequest(Person P){
        DBConnection.setupDbConnection("Request");
        stmt = DBConnection.getStmt();
        System.out.println("Please enter request id");
        int in = input.nextInt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+in+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
            }
            String sql = "UPDATE RIDES set STATUS = 'D' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //driver method
    public static void makeOffer(Person p){
        DBConnection.setupDbConnection("Request");
        stmt = DBConnection.getStmt();
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
            System.out.println("Enter a suitable price:");
            int price = input.nextInt();
            String sql = "UPDATE RIDES set PRICE = "+price+" WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            sql = "UPDATE RIDES set DRIVER = '"+p.getUserName()+"' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}