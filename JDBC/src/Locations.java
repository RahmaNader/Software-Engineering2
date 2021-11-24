import java.sql.*;
import java.util.Scanner;

public class Locations {
    public String location;
    public String user;
    public int ID;
    private static Statement stmt;
    private final static Scanner input = new Scanner(System.in);

    public static void addFavourite(Person p) {
        DBConnection.setupDbConnection("Locations");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened LOCATIONS successfully");
            System.out.println("Source: ");
            String in = input.nextLine();
            String sql = "INSERT INTO LOCATIONS (LOCATION,USERNAME) " +
                    "VALUES (" + "'" + in + "'" + "," + "'" + p.getUserName() + "'" + ");";
            stmt.executeUpdate(sql);
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Location added successfully");
    }
    public static void displayFavorite(Person p){
        DBConnection.setupDbConnection("Locations");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES" +
                    "            INNER JOIN LOCATIONS" +
                    "            ON RIDES.SOURCE = LOCATIONS.LOCATION" +
                    "            WHERE LOCATIONS.USERNAME = '"+p.getUserName()+"' AND RIDES.STATUS = 'P';");
            while (rs.next()) {
                Print.rideData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}