import java.sql.*;
import java.util.Scanner;

public class UserDriverDB {
    private static Connection connection;
    private static Statement stmt;
    private static UserDriverDB uniqueInstance;
    private final Scanner input = new Scanner(System.in);

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
        try {
            String sql = "CREATE TABLE IF NOT EXISTS USER " +
                    "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    " STATUS      TEXT CHECK( STATUS IN ('S','A') )   NOT NULL DEFAULT 'A', " +
                    " EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ," +
                    " PASSWORD        CHAR(30)     NOT NULL," +
                    "RATING  DOUBLE DEAFULT'NULL');";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS DRIVER " +
                    "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    " STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'P', " +
                    " EMAIL      CHAR(30) DEFAULT 'null@gmail.com' ," +
                    " NATIONALID      CHAR(30) NOT NULL ," +
                    " LICENSE      CHAR(30) NOT NULL ," +
                    " PASSWORD        CHAR(30)     NOT NULL," +
                    "RATING DOUBLE DEFAULT'NULL');";
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
            sql = "INSERT INTO USER (USERNAME,NAME,MOBILE,PASSWORD,RATING) " +
                    "VALUES (" + "'" + user.getUserName() + "'" + "," +
                    "'" + user.getName() + "'" + "," +
                    "'" + user.getMobileNum() + "'" + "," +
                    "'" + user.getPassword() + "'" + ",'NULL');";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
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
            sql = "INSERT INTO DRIVER (USERNAME,NAME,MOBILE,PASSWORD,NATIONALID,LICENSE,RATING) " +
                    "VALUES (" + "'" + driver.getUserName() + "'" + "," +
                    "'" + driver.getName() + "'" + "," +
                    "'" + driver.getMobileNum() + "'" + "," +
                    "'" + driver.getPassword() + "'" + "," +
                    "'" + driver.getNationalID()+ "'" + "," +
                    "'" + driver.getDriverLicense() + "'" + ",'NULL');";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Driver created successfully");
    }

    public void register() {
        int in;
        System.out.println("1- Register as User"+"\n"+"2- Register as Driver");
        in = input.nextInt();
        if (in == 1) registerUser();
        else registerDriver();
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
                System.out.println ("RATING = "+rs.getDouble ( "rating" ) );
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
                System.out.println ("RATING = "+rs.getDouble ( "rating" ) );
                System.out.println("///////////////////////////////");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }

    public Person loginDriver() {
        Person p = null;
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
                p.setRating (rs.getDouble ( "rating" ));
                System.out.println("///////////////////////////////");
            }
            rs.close();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Person loginUser(){
        Person p = null;
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
                p.setRating (rs.getDouble ( "rating" ));
                System.out.println("///////////////////////////////");
            }
            rs.close();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static void rateDriver(Person p) throws SQLException {
        Scanner scan = new Scanner ( System.in );
        double rate; // add the list of users coupled with the drivers

        ///// To make the person p saved in the list of drivers with its rate and username///

        Connection connection = null;
        Statement statement = null;
        System.out.print ("Enter the username: " );
        String username = scan.nextLine ();
        System.out.print ("Enter the rating from 1 to 5 (1 worst, 5 best) :" );
        double newRate = scan.nextDouble ();
        double currRate = Double.parseDouble ( "SELECT RATING\n WHERE USERNAME = '"+username+"';" );
        currRate = (currRate + newRate)/2;
        String sql = "UPDATE USER\n" + "SET\n RATING="+ currRate+"\n WHERE USERNAME = '"+username+"';";
        statement.executeUpdate ( sql );

    }

    public void viewAvgRating(Person p) throws SQLException {
        String sql = "SELECT RATING\n FROM PERSON\n WHERE USERNAME = '"+p.getUserName ()+"';";
        ResultSet rs = stmt.executeQuery ( sql );
        System.out.println (rs.getDouble ( "rating" ) );
        rs.close ();
    }
}