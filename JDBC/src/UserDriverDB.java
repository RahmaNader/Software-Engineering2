import java.sql.*;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Vector;


public class UserDriverDB {
    private final Vector<User> users;
    private final Vector<Driver> drivers;
    private static Connection connection;
    private static Statement stmt;
    private static UserDriverDB uniqueInstance;
    private Scanner input = new Scanner(System.in);

    private static void setupDbConnection(){
        try{
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            stmt = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private UserDriverDB() {
        setupDbConnection();
        users = new Vector<>();
        drivers = new Vector<>();
        try {
            String sql = "CREATE TABLE IF NOT EXISTS USER " +
                    "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    " STATUS      TEXT CHECK( STATUS IN ('S','A') )   NOT NULL DEFAULT 'A', " +
                    " EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ," +
                    " PASSWORD        CHAR(30)     NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS DRIVER " +
                    "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    " STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'P', " +
                    " EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ," +
                    " NATIONALID      CHAR(30) NOT NULL ," +
                    " LICENSE      CHAR(30) NOT NULL ," +
                    " PASSWORD        CHAR(30)     NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Opened persons successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDriverDB getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new UserDriverDB();
        }
        return uniqueInstance;
    }

    public void registerUser() {
        String in;
        try {
            System.out.println("Opened persons successfully");
            User user = new User();
            String sql;
            System.out.println("User name: ");
            in = input.nextLine();
            user.setUserName(in);
            System.out.println("Name: ");
            in = input.nextLine();
            user.setName(in);
            System.out.println("Mobile number: ");
            in = input.nextLine();
            user.setMobileNum(in);
            System.out.println("Password: ");
            in = input.nextLine();
            user.setPassword(in);
            sql = "INSERT INTO USER (USERNAME,NAME,MOBILE,PASSWORD) " +
                    "VALUES (" + "'" + user.getUserName() + "'" + "," +
                    "'" + user.getName() + "'" + "," +
                    "'" + user.getMobileNum() + "'" + "," +
                    "'" + user.getPassword() + "'" + ");";
            if (!users.contains(user)) {
                users.add(user);
                stmt.executeUpdate(sql);
            } else {
                System.out.println("User name already exists");
            }
        } catch (EmptyStackException  | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User created successfully");
    }

    public void registerDriver() {
        String in;
        try {
            System.out.println("Opened persons successfully");
            Driver driver = new Driver();
            String sql;
            System.out.println("User name: ");
            in = input.nextLine();
            driver.setUserName(in);
            System.out.println("Name: ");
            in = input.nextLine();
            driver.setName(in);
            System.out.println("Mobile number: ");
            in = input.nextLine();
            driver.setMobileNum(in);
            System.out.println("Password: ");
            in = input.nextLine();
            driver.setPassword(in);
            System.out.println("National ID: ");
            in = input.nextLine(); driver.setNationalID(in);
            System.out.println("Driver License: ");
            in = input.nextLine(); driver.setDriverLicense(in);
            sql = "INSERT INTO DRIVER (USERNAME,NAME,MOBILE,PASSWORD,NATIONALID,LICENSE) " +
                    "VALUES (" + "'" + driver.getUserName() + "'" + "," +
                    "'" + driver.getName() + "'" + "," +
                    "'" + driver.getMobileNum() + "'" + "," +
                    "'" + driver.getPassword() + "'" + "," +
                    "'" + driver.getNationalID()+ "'" + "," +
                    "'" + driver.getDriverLicense() + "'" + ");";
            if (!drivers.contains(driver)) {
                drivers.add(driver);
                stmt.executeUpdate(sql);
            } else {
                System.out.println("User name already exists");
            }
        } catch (EmptyStackException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Driver created successfully");
    }

    public void register() {
        String in;
        System.out.println("Do you want to register as a driver?[Y/N]");
        in = input.nextLine();
        if (in.equals("Y") || in.equals("y")) registerDriver();
        else registerUser();
    }

    public void loadUserDB() {
        try {
            System.out.println("Opened persons successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                System.out.println();
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                User u = new User();
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setUserName(rs.getString("username"));
                u.setMobileNum(rs.getString("mobile"));
                if (!users.contains(u)) {
                    users.add(u);
                }
                System.out.println("///////////////////////////////");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }

    public void loadDriverDB() {
        try {
            System.out.println("Opened persons successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER;");
            while (rs.next()) {
                System.out.println();
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("NATIONAL ID = " + rs.getString("nationalid"));
                System.out.println("LICENSE = " + rs.getString("license"));
                Driver d = new Driver();
                d.setName(rs.getString("name"));
                d.setPassword(rs.getString("password"));
                d.setUserName(rs.getString("username"));
                d.setMobileNum(rs.getString("mobile"));
                d.setNationalID(rs.getString("nationalid"));
                d.setDriverLicense(rs.getString("license"));
                if (!drivers.contains(d)) {
                    drivers.add(d);
                }
                System.out.println("///////////////////////////////");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }

    public Person login() {
        Person p = null;
        String username, password;
        System.out.println("User name: ");
        username = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.elementAt(i).getUserName().equals(username)) {
                    if (users.get(i).getPassword().equals(password)) {
                        System.out.println("Authentication is successful");
                        p = users.elementAt(i);
                        return p;
                    }
                }
            }
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.elementAt(i).getUserName().equals(username)) {
                    if (drivers.get(i).getPassword().equals(password)) {
                        System.out.println("Authentication is successful");
                        p = drivers.elementAt(i);
                        return p;
                    }
                }
            }
            System.out.println("Wrong username or password");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void rateDriver(Person p){}

    public void viewAvgRating(Person p){}
}