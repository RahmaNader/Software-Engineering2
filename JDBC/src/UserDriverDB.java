import java.sql.*;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Vector;

public class UserDriverDB {
    private final Vector<User> users;

    private final Vector<Driver> drivers;

    public UserDriverDB() {
        users = new Vector<>();
        drivers = new Vector<>();
        Connection connection;
        Statement stmt;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            stmt = connection.createStatement();
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
                    " STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'A', " +
                    " EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ," +
                    " NATIONALID      CHAR(30) NOT NULL ," +
                    " LICENSE      CHAR(30) NOT NULL ," +
                    " PASSWORD        CHAR(30)     NOT NULL)";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened persons successfully");
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerUser() {
        Scanner input = new Scanner(System.in);
        String in;
        Connection connection;
        Statement stmt;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            connection.setAutoCommit(false);
            System.out.println("Opened persons successfully");
            stmt = connection.createStatement();
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
            }
            else {
                System.out.println("User name already exists");
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (EmptyStackException | SQLException | ClassNotFoundException e) {
            //System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("User created successfully");
    }

    public void registerDriver() {
        Scanner input = new Scanner(System.in);
        String in;
        Connection connection;
        Statement stmt;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            connection.setAutoCommit(false);
            System.out.println("Opened persons successfully");
            stmt = connection.createStatement();
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
            }
            else {
                System.out.println("User name already exists");
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (EmptyStackException | SQLException | ClassNotFoundException e) {
            //System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Driver created successfully");
    }

    public void register() {
        Scanner input = new Scanner(System.in);
        String in;
        System.out.println("Do you want to register as a driver?[Y/N]");
        in = input.nextLine();
        if (in.equals("Y") || in.equals("y")) registerDriver();
        else registerUser();
    }

    public void loadUserDB() {
        Connection connection;
        Statement stmt;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            connection.setAutoCommit(false);
            System.out.println("Opened persons successfully");
            stmt = connection.createStatement();
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
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    public void loadDriverDB() {
        Connection connection;
        Statement stmt;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:persons.db");
            connection.setAutoCommit(false);
            System.out.println("Opened persons successfully");
            stmt = connection.createStatement();
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
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    public Person login() {
        Person p = null;
        Scanner input = new Scanner(System.in);
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
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void rateDriver(Person p){}

    public void viewAvgRating(Person p){}
}
