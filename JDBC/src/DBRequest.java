import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DBRequest implements IDBRequest{
    private static Statement stmt;

    public void requestRide( Person p, String source, String destination, LocalDate date, int numOfPassengers ){
        Notification notification = new Notification();
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened database successfully");
            Rides ride = new Rides();
            ride.setSource(source);
            ride.setDestination(destination);
            ride.setUser(p.getUserName());
            String sql = "INSERT INTO RIDES (SOURCE,DESTINATION,USER,DATE, NUMOFPASSENGERS) " +
                    "VALUES ("+ "'" + ride.getSource() + "'" + "," + "'" + ride.getDestination() + "'" + "," + "'" + p.getUserName() + "'"
                    + "," + "'" + ride.getDate ()  + "'" + "," + "'" + ride.getNumOfPassengers ()  + "'"  + ");";
            stmt.executeUpdate(sql);
            DBConnection.closeConnection();
            notification.setUpNotification();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }
    
    public void viewRequests(Person p){
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
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
    
    public void acceptRequest(int id){
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+id+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
            }
            String sql = "UPDATE RIDES set STATUS = 'A' WHERE ID ="+id+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void cancelRequest(Person P, int id){
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+id+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
            }
            String sql = "UPDATE RIDES set STATUS = 'D' WHERE ID ="+id+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void makeOffer(Person p, int id, int price){
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES WHERE ID = "+id+";");
            while (rs.next()) {
                System.out.println();
                System.out.println("ID = " + rs.getString("id"));
                System.out.println("SOURCE = "+rs.getString("source"));
                System.out.println("DESTINATION = "+rs.getString("destination"));
                System.out.println ("DATE = "+rs.getDate ( "date" ) );
                System.out.println ("NUMOFPASSENGERS = " + rs.getInt ( "numofpassengers" ) );
            }
            String sql = "UPDATE RIDES set PRICE = "+price+" WHERE ID ="+id+";";
            stmt.executeUpdate(sql);
            sql = "UPDATE RIDES set DRIVER = '"+p.getUserName()+"' WHERE ID ="+id+";";
            stmt.executeUpdate(sql);
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public int checkNumOfPassengers (int id , Date date)
    {
        int num = 1;
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            ResultSet rs = stmt.executeQuery ( "SELECT * FROM RIDES WHERE ID=" + id + " AND DATE="+ date + ";" );
            num = rs.getInt ( "numofpassengers" );
            rs.close ();
            DBConnection.closeConnection ();
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        return num;
    }

    public boolean checkFirstRide (int id) throws SQLException {
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery ( "SELECT USER FROM RIDES WHERE ID = "+id +";" );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        rs.close ();
        String userName = rs.getString ( "USER" );
        ResultSet res = stmt.executeQuery ( "SELECT COUNT(ID) AS CNT FROM RIDES HAVING USER = "+"'"+ userName +"'"+";" );
        int cnt = res.getInt ( "CNT" );
        res.close ();
        return cnt < 1;
    }



}
