import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class PersonDB {
    private Vector<Person> people;

    public PersonDB() {
        people = new Vector<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PERSON " +
                    "(USERNAME CHAR(50) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    " STATUS      TEXT CHECK( STATUS IN ('S','P','A') )   NOT NULL DEFAULT 'P', " +
                    " TYPE      TEXT CHECK( TYPE = 'D' OR TYPE = 'U')   DEFAULT 'U'," +
                    " NATIONALID      CHAR(30) DEFAULT '0000000000' ," +
                    " LICENSE      CHAR(30) DEFAULT '0000000000' ," +
                    " PASSWORD        CHAR(30)     NOT NULL)";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened person successfully");
            stmt.close();
            connection.close();
        } catch (Exception e) {
            if (e.getMessage().contains("table PERSON already exists")) {
                this.loadDB();
            } //else System.out.println(e.getMessage());
        }
    }

    public void register() {
        Scanner input = new Scanner(System.in);
        String in;
        Connection connection = null;
        Statement stmt = null;
        boolean driver;
        try {
            System.out.println("Do you want to register as a driver?[Y/N]");
            in = input.nextLine();
            if (in.equals("Y") || in.equals("y")) driver = true;
            else driver = false;
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            connection.setAutoCommit(false);
            System.out.println("Opened person successfully");
            stmt = connection.createStatement();
            Person person = new Person();
            String sql, nationalid, license;
            System.out.println("User name: ");
            in = input.nextLine();
            person.setUserName(in);
            System.out.println("Name: ");
            in = input.nextLine();
            person.setName(in);
            System.out.println("Mobile number: ");
            in = input.nextLine();
            person.setMobileNum(in);
            System.out.println("Password: ");
            in = input.nextLine();
            person.setPassword(in);
            if (driver) {
                System.out.println("National ID: ");
                nationalid = input.nextLine();
                System.out.println("Driver License: ");
                license = input.nextLine();
                sql = "INSERT INTO PERSON (USERNAME,NAME,MOBILE,PASSWORD,TYPE,NATIONALID,LICENSE) " +
                        "VALUES (" + "'" + person.getUserName() + "'" + "," +
                        "'" + person.getName() + "'" + "," +
                        "'" + person.getMobileNum() + "'" + "," +
                        "'" + person.getPassword() + "'" + "," +
                        "'D'" + "," +
                        "'" + nationalid + "'" + "," +
                        "'" + license + "'" + ");";
            } else {
                sql = "INSERT INTO PERSON (USERNAME,NAME,MOBILE,PASSWORD,TYPE,NATIONALID,LICENSE) " +
                        "VALUES (" + "'" + person.getUserName() + "'" + "," +
                        "'" + person.getName() + "'" + "," +
                        "'" + person.getMobileNum() + "'" + "," +
                        "'" + person.getPassword() + "'" + "," +
                        "'U'" + "," +
                        "'NULL'" + "," +
                        "'NULL'" + ");";
            }
            stmt.executeUpdate(sql);
            if (!people.contains(person)) {
                people.add(person);
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }

    public void loadDB() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("UserDriverDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PERSON;");
            while (rs.next()) {
                System.out.println("////////////////////");
                System.out.println("USER NAME = " + rs.getString("username"));
                System.out.println("NAME = " + rs.getString("name"));
                System.out.println("MOBILE = " + rs.getString("mobile"));
                System.out.println("PASSWORD = " + rs.getString("password"));
                System.out.println("STATUS = " + rs.getString("status"));
                System.out.println("TYPE = " + rs.getString("type"));
                Person p = new Person();
                p.setName(rs.getString("name"));
                p.setPassword(rs.getString("password"));
                p.setUserName(rs.getString("username"));
                p.setMobileNum(rs.getString("mobile"));
                if (!people.contains(p)) {
                    people.add(p);
                }
                System.out.println("///////////////////////////////");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public boolean login() {
        Scanner input = new Scanner(System.in);
        String username, password;
        System.out.println("User name: ");
        username = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();
        try {
            for (int i = 0; i < people.size(); i++) {
                if (people.elementAt(i).getUserName().equals(username)) {
                    if (people.get(i).getPassword().equals(password)) {
                        System.out.println("Authentication is successful");
                        return true;
                    }
                }
            }
            System.out.println("Wrong username or password");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}