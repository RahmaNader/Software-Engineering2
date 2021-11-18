import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class RideDB {
    //System.getProperty("user.dir")
    private List<Ride> rides;

    public RideDB() {
        rides = new Vector<> ();
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("RideDB");
            connection = DriverManager.getConnection("jdbc:sqlite:ride.db");

            stmt = connection.createStatement();
            String sql = "CREATE TABLE RIDE " +
                    "(RIDEID INT PRIMARY KEY NOT NULL  ," +
                    " USERNAME TEXT  NOT NULL  , " +
                    " DRIVERNAME TEXT  NOT NULL , " +
                    " PRICE  INT  NOT NULL, " +
                    " SRC  TEXT   NOT NULL," +
                    " DEST TEXT   NOT NULL)";

            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt.close();
            connection.close();
        } catch (Exception e) {
            if (e.getMessage().contains("table RIDE already exists")) {
                this.loadDB();
            } else System.out.println(e.getMessage());
        }
    }

    public void addRide() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("PersonDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            Ride ride = new Ride ();
            Scanner input = new Scanner(System.in);
            String in;
            System.out.print("Source location: ");
            in = input.nextLine();
            ride.setSrc ( in );
            System.out.print("\n" + "Destination location: ");
            in = input.nextLine();
            ride.setDest ( in );
            System.out.print("\n" + "Price: ");
            int num = input.nextInt ();
            ride.setPrice ( num );

            // send username and drivername//
            // to get the driver object or user object, you would get them by username primary key" //

            String sql = "INSERT INTO RIDE (SRC,DEST,PRICE,USERNAME,DRIVERNAME) " +
                    "VALUES (" + "'" + ride.getSrc () + "'" + "," + "'" + ride.getDest ()
                    + "'" + "," + "'" + ride.getPrice ()
                    + "'" + "," + "'" + ride.getUser ( ).getUserName ( )
                    + "'" + "," + "'" +  ride.getDriver ().getUserName ()
                    + "'" + ");";
            stmt.executeUpdate(sql);
            if (!rides.contains(ride)) {
                rides.add(ride);
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    private void loadDB() {
        // displays all rides database //

        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("RideDB");
            connection = DriverManager.getConnection("jdbc:sqlite:ride.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDE;");
            while (rs.next()) {
                //int rideID = rs.getInt ("rideID");
                String username = rs.getString("username");
                String drivername = rs.getString("drivername");
                String source = rs.getString("src");
                String destination = rs.getString("dest");
                int price = rs.getInt ("price");
                System.out.println("USER NAME = " + username);
                System.out.println("PRICE = " + price);
                System.out.println("SOURCE LOCATION = " + source);
                System.out.println("DESTINATION LOCATION = " + destination);
                System.out.println("DRIVER NAME = " + drivername);
                System.out.println();
                Ride ride = new Ride ( source,destination,price,username,drivername );
                if (!rides.contains(ride)) {
                    rides.add(ride);
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }


    public void find() {
        // to search for specific ride//

        Scanner input = new Scanner(System.in);
        String username,source,destination;
        System.out.print("Your Username: ");
        username = input.nextLine();
        System.out.print("Source location: ");
        source = input.nextLine();
        System.out.print("\n" + "Destination: ");
        destination = input.nextLine();
        try {
            boolean flag=false;
            for (Ride value : rides) {
                if ( value.getUser ( ).getUserName ( ).equals ( username ) ) {
                    if ( value.getSrc ( ).equals ( source ) ) {
                        if ( value.getDest ( ).equals ( destination ) ) {
                            flag = true;
                            System.out.println ( "Source location: " + value.getSrc ( ) + " , Destination location: "
                                    + value.getDest ( ) + " , Ride cost: " + value.getPrice ( ) +
                                    " , Trip State: " + value.getRideState ( ) + " , Driver Username: " + value.getDriver ( ).getUserName ( )
                            );
                            break;
                        }
                    }
                }

            }
            if(!flag) System.out.println("Wrong data!");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
