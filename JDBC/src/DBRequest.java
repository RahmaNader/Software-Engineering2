import java.sql.ResultSet;
import java.sql.Statement;

public class DBRequest implements IDBRequest{
    private static Statement stmt;

    public void requestRide(Person p, String source, String destination){
        Notification notification = new Notification();
        DBConnection.setupDbConnection("DBRequest");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened database successfully");
            Rides ride = new Rides();
            ride.setSource(source);
            ride.setDestination(destination);
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
}
