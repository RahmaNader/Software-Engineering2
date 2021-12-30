import java.sql.ResultSet;
import java.sql.Statement;

public class DBAdmin implements IDBAdmin{

    public Statement stmt;

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
