import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
    private static Admin adminInstance;
    private static Connection connection;
    private static Statement stmt;

    private Admin(){
        //private admin constructor
    }

    private static void setupDbConnection(){
        try{
            Class.forName("Admin");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            stmt = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Admin getInstance(){
        if(adminInstance == null){
            adminInstance = new Admin();
            setupDbConnection();
        }
        return adminInstance;
    }

    public void listAllDriverRequests(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER WHERE STATUS = 'P';");
            while (rs.next()) {
                System.out.println();
                System.out.println();
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("NATIONAL ID = " + rs.getString("nationalid"));
                System.out.println("LICENSE = " + rs.getString("license"));
                System.out.println();
                System.out.println();
            }
            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAllDrivers(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER;");
            while (rs.next()) {
                System.out.println();
                System.out.println();
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("NATIONAL ID = " + rs.getString("nationalid"));
                System.out.println("LICENSE = " + rs.getString("license"));
                System.out.println();
                System.out.println();
            }
            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAllUsers(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                System.out.println();
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                System.out.println("STATUS = "+rs.getString("status"));
                System.out.println();
            }
            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void suspendDriver(String userName){
        try {
            stmt.executeUpdate("UPDATE DRIVER SET STATUS = 'S' WHERE USERNAME = "+"'"+userName+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void suspendUser(String userName){
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'S' WHERE USERNAME = "+"'"+userName+"'");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateDriver(String userName){
        try{
            stmt.executeUpdate("UPDATE DRIVER SET STATUS = 'A' WHERE USERNAME = "+"'"+userName+"'");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateUser(String userName){
        try{
            stmt.executeUpdate("UPDATE USER SET STATUS = 'A' WHERE USERNAME = "+"'"+userName+"'");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}