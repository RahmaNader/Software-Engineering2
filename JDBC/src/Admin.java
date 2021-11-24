import java.sql.*;

public class Admin {
    private static Admin adminInstance;
    private static Statement stmt;
    //admin username and password
    String username = "admin";
    String password = "admin";

    private Admin(){
        //private admin constructor
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static Admin getInstance(){
        if(adminInstance == null){
            adminInstance = new Admin();
            DBConnection.setupDbConnection("Admin");
            stmt = DBConnection.getStmt();
        }
        return adminInstance;
    }

    public void listAllDriverRequests(){
        DBConnection.setupDbConnection("Admin");
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
        DBConnection.setupDbConnection("Admin");
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
        DBConnection.setupDbConnection("Admin");
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
        DBConnection.setupDbConnection("Admin");
        stmt = DBConnection.getStmt();
        try {
            stmt.executeUpdate("UPDATE DRIVER SET STATUS = 'S' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void suspendUser(String userName){
        DBConnection.setupDbConnection("Admin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'S' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateDriver(String userName){
        DBConnection.setupDbConnection("Admin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE Driver SET STATUS = 'A' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateUser(String userName){
        DBConnection.setupDbConnection("Admin");
        stmt = DBConnection.getStmt();
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'A' WHERE USERNAME = "+"'"+userName+"'");
            DBConnection.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}