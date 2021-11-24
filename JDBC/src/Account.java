import java.sql.*;
import java.util.Scanner;

public class Account {
    private static final Scanner input = new Scanner(System.in);
    private static Statement stmt;
    public static void register() {
        int in;
        System.out.println("1- Register as User"+"\n"+"2- Register as Driver");
        in = input.nextInt();
        input.nextLine();
        System.out.println("UserName: ");
        String userName = input.nextLine();
        System.out.println("Name: ");
        String name = input.nextLine();
        System.out.println("Mobile number: ");
        String mobile = input.nextLine();
        System.out.println("Password: ");
        String password = input.nextLine();
        System.out.println("Email: (Keep it empty if you want it's optional) ");
        String email = input.nextLine();
        if(email.isBlank()) email = "null";
        if (in == 1){
            //user
            DBConnection.setupDbConnection("Account");
            stmt = DBConnection.getStmt();
            User user = new User(name, userName, mobile, email, password);
            try{
                String sql = "INSERT INTO USER (USERNAME,NAME,MOBILE,PASSWORD,EMAIL) " +
                        "VALUES (" + "'" + user.getUserName() + "'" + "," +
                        "'" + user.getName() + "'" + "," +
                        "'" + user.getMobileNum() + "'" + "," +
                        "'" + user.getPassword() + "'" + "," +
                        "'" + user.getEmail() + "'" + ");";
                stmt.execute(sql);
                DBConnection.closeConnection();
                System.out.println("User created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(in == 2){
            //driver
            System.out.println("National ID: ");
            String natId = input.nextLine();
            System.out.println("Driver License: ");
            String lic = input.nextLine();
            DBConnection.setupDbConnection("Account");
            stmt = DBConnection.getStmt();
            Driver driver = new Driver(name, userName, mobile, email, password, natId, lic);
            try{
                String sql = "INSERT INTO DRIVER (USERNAME,NAME,MOBILE,PASSWORD,EMAIL,NATIONALID,LICENSE) " +
                        "VALUES (" + "'" + driver.getUserName() + "'" + "," +
                        "'" + driver.getName() + "'" + "," +
                        "'" + driver.getMobileNum() + "'" + "," +
                        "'" + driver.getPassword() + "'" + "," +
                        "'" + driver.getEmail()+ "'" + "," +
                        "'" + driver.getNationalID()+ "'" + "," +
                        "'" + driver.getDriverLicense() + "'" + ");";
                stmt.executeUpdate(sql);
                DBConnection.closeConnection();
                System.out.println("Driver created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Invalid Input\n=======================");
    }
    public static Driver loginDriver() {
        DBConnection.setupDbConnection("Account");
        stmt = DBConnection.getStmt();
        Driver p = new Driver();
        String username, password;
        System.out.println("User name: ");
        username = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER WHERE USERNAME = '"+username+"'  " +
                    "AND PASSWORD = '"+password+"' AND STATUS = 'A';");
            while (rs.next()) {
                System.out.println();
                p.setUserName(rs.getString("username"));
                p.setUserName(rs.getString("name"));
                p.setMobileNum(rs.getString("mobile"));
                p.setPassword(rs.getString("password"));
                System.out.println("///////////////////////////////");
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    public static User loginUser(){
        DBConnection.setupDbConnection("Account");
        stmt = DBConnection.getStmt();
        User p  = new User();
        String username, password;
        System.out.println("User name: ");
        username = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE USERNAME = '"+username+"'  " +
                    "AND PASSWORD = '"+password+"' AND STATUS = 'A';");
            while (rs.next()) {
                System.out.println();
                p.setUserName(rs.getString("username"));
                p.setUserName(rs.getString("name"));
                p.setMobileNum(rs.getString("mobile"));
                p.setPassword(rs.getString("password"));
                System.out.println("///////////////////////////////");
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
