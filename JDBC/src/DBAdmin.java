import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBAdmin implements IDBAdmin{

    public Statement stmt;

    static IDBEvent idbEvent = new DBEvent ();

    public void displayEvent ( ) {
        try {
            idbEvent.displayEvent ();
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
    public void displayEventByEventName (EventName eventName)
    {
        try {
            idbEvent.displayEventByEventName ( eventName );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
    public void displayEventByDate ( Date date)
    {
        try {
            idbEvent.displayEventByDate ( date );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
    public void displayEventByUser (String user)
    {
        try {
            idbEvent.displayEventByUser ( user );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
    public void displayEventByDriver (String driver)
    {
        try {
            idbEvent.displayEventByDriver ( driver );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }
    public void displayEventByRideID (int id)
    {
        try {
            idbEvent.displayEventByRideID ( id );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    public void listAllDriverRequests(){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER WHERE STATUS = 'P';");
            while (rs.next()) {
                Print.driverData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void listAllDrivers(){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER;");
            while (rs.next()){
                Print.driverData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void listAllUsers(){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                Print.userData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void suspendDriver(String userName){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try {
            stmt.executeUpdate("UPDATE DRIVER SET STATUS = 'S' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void suspendUser(String userName){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'S' WHERE main.USER.USER_NAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void activateDriver(String userName){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE Driver SET STATUS = 'A' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateUser(String userName){
        DBConnection.setupDbConnection("DBAdmin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'A' WHERE main.USER.USER_NAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
