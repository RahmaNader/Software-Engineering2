import java.sql.*;

public class Rides {
    private String source;
    
    private String destination;
    
    private String user;
    
    private String driver;
    
    private int ID;
    
    private static Statement stmt;

    private Date date;

    private int numOfPassengers;

    public static void viewRides(){
        DBConnection.setupDbConnection("Rides");
        stmt = DBConnection.getStmt();
        //data base
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE STATUS != 'A' OR STATUS !='D';");
            while (rs.next()) {
                Print.rideData(rs);
            }
            DBConnection.closeConnection();
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public int getID() {
        return ID;
    }

    public Rides() {
       
    }

    public Rides(String source, String destination, String user,int ID, int numOfPassengers, Date date) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.ID = ID;
        this.numOfPassengers = numOfPassengers;
        this.date =  date;
    }

    public static Rides getRideByID (int id) throws SQLException {
        DBConnection.setupDbConnection("Rides");
        stmt = DBConnection.getStmt();

        ResultSet res = stmt.executeQuery ( "SELECT * FROM RIDES WHERE ID = "+id+";");
        Rides ride = new Rides (  );
        ride.source = res.getString ( "source" );
        ride.destination = res.getString ( "destination" );
        ride.driver = res.getString ( "driver" );
        ride.user = res.getString ( "user" );
        ride.ID = res.getInt ( "id" );
        ride.numOfPassengers = res.getInt ( "numofpassengers" );
        ride.date = res.getDate ( "date" );

        try {
            res.close();
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        DBConnection.closeConnection();
        return ride;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Date getDate ( ) {
        return date;
    }

    public void setDate ( Date date ) {
        this.date = date;
    }

    public int getNumOfPassengers ( ) {
        return numOfPassengers;
    }

    public void setNumOfPassengers ( int numOfPassengers ) {
        this.numOfPassengers = numOfPassengers;
    }
}
