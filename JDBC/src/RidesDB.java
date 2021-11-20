import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class RidesDB {
    private final Vector<Rides> rides;
    Scanner input = new Scanner(System.in);

    public RidesDB() {
        rides = new Vector<>();
        Connection connection;
        Statement stmt;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:rides.db");
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RIDES " +
                    "(ID        INTEGER    PRIMARY KEY      AUTOINCREMENT," +
                    " SOURCE           CHAR(50)    NOT NULL, " +
                    " DESTINATION            CHAR(50)     NOT NULL, " +
                    " DRIVER            CHAR(50)     , " +
                    " USER            CHAR(50)     NOT NULL , " +
                    " PRICE            FLOAT(2)     DEFAULT 0 , " +
                    " STATUS      TEXT CHECK( STATUS IN ('A','D','P') )   NOT NULL DEFAULT 'P')";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened Rides successfully");
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadDB() {
        Connection connection;
        Statement stmt;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:rides.db");
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
                Rides ride = new Rides(rs.getString("source"), rs.getString("destination"), rs.getString("user"),Integer.parseInt(rs.getString("id")));
                //set status accordingly
                if (!rides.contains(ride)) {
                    rides.add(ride);
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void requestRide(Person p) {
        String in;
        Connection connection;
        Statement stmt;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:rides.db");
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
            if (!rides.contains(ride)) {
                rides.add(ride);
                stmt.executeUpdate(sql);
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }

    public void viewRequests(Person p){
        Connection connection;
        Statement stmt;
        int in;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:rides.db");
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
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("////////////////////");
                Rides ride = new Rides(rs.getString("source"), rs.getString("destination"), rs.getString("user"),Integer.parseInt( rs.getString("id")));
                //set status accordingly
                if (!rides.contains(ride)) {
                    rides.add(ride);
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("1- Accept offer?"+"\n"+"2- Deny offer?");
        in = input.nextInt();
        if(in == 1) this.acceptRequest(p);
        else if(in == 2) this.refuseRequest(p);
    }

    public void acceptRequest(Person P){
        System.out.println("Please enter request id");
        int in = input.nextInt();
        Connection connection;
        Statement stmt;
        try {
            Class.forName("RidesDB");
            connection = DriverManager.getConnection("jdbc:sqlite:rides.db");
            connection.setAutoCommit(false);
            System.out.println("Opened rides successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+in+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
                Rides ride = new Rides(rs.getString("source"), rs.getString("destination"), rs.getString("user"),Integer.parseInt(rs.getString("id")));
                //set status accordingly
                if (!rides.contains(ride)) {
                    rides.add(ride);
                }
            }
            String sql = "UPDATE RIDES set STATUS = 'A' WHERE ID ="+in+";";
            stmt.executeUpdate(sql);
            connection.commit();
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void refuseRequest(Person P){}

    public void makeOffer(Person p){}

    public void displayFavourite(){
        for(Rides ride: rides ){

        }
    }
}

