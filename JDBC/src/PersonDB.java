import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class PersonDB {
    //System.getProperty("user.dir")
    private Vector<Person> people;

    public PersonDB() {
        people= new Vector<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("PersonDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");

            stmt = connection.createStatement();
            String sql = "CREATE TABLE PERSON " +
                    "(USERNAME CHAR(20) PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " MOBILE            CHAR(20)     NOT NULL, " +
                    "status      TEXT CHECK( status IN ('S','P','A') )   NOT NULL DEFAULT 'P', "+
                    " PASSWORD        CHAR(30)      NOT NULL)";
            stmt.executeUpdate(sql);
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt.close();
            connection.close();
        } catch (Exception e) {
            if (e.getMessage().contains("table PERSON already exists")) {
                this.loadDB();
            } else System.out.println(e.getMessage());
        }
    }
    public void register() {
        Scanner input = new Scanner(System.in); String in;
        Connection connection = null;
        Statement stmt = null;
        boolean driver = false;
        try {
            System.out.println("Do you want to register as a driver?[Y/N]");
            in = input.nextLine();
            if(in.equals("Y")) driver = true;
            else driver = true;
            Class.forName("PersonDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            Person person = new Person();
            System.out.print("User name: ");
            in = input.nextLine();
            person.setUserName(in);
            System.out.print("\n" + "Name: ");
            in = input.nextLine();
            person.setName(in);
            System.out.print("\n" + "Mobile number: ");
            in = input.nextLine();
            person.setMobileNum(in);
            System.out.print("\n" + "Password: ");
            in = input.nextLine();
            person.setPassword(in);
            if(driver){
                System.out.print("\n" + "National ID: ");
                in = input.nextLine();
                person.setMobileNum(in);
                System.out.print("\n" + "Driver License: ");
                in = input.nextLine();
                person.setPassword(in);
            }
            String sql = "INSERT INTO PERSON (USERNAME,NAME,MOBILE,PASSWORD) " +
                    "VALUES (" + "'" + person.getUserName() + "'" + "," + "'" + person.getName() + "'" + "," + "'" + person.getMobileNum() + "'" + "," + "'" + person.getPassword() + "'" + ");";
            stmt.executeUpdate(sql);
            if (!people.contains(person)) {
                people.add(person);
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    private void loadDB() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("PersonDB");
            connection = DriverManager.getConnection("jdbc:sqlite:person.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PERSON;");
            while (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String password = rs.getString("password");
                String staus = rs.getString("status");
                System.out.println("USER NAME = " + username);
                System.out.println("NAME = " + name);
                System.out.println("MOBILE = " + mobile);
                System.out.println("PASSWORD = " + password);
                System.out.println();
                Person p = new Person(username,name,mobile,password);
                if (!people.contains(p)) {
                    people.add(p);
                }
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
    public void login() {
        Scanner input = new Scanner(System.in);
        String username,password;
        System.out.print("User name: ");
        username = input.nextLine();
        System.out.print("\n" + "Password: ");
        password = input.nextLine();
        try {
            boolean flag=false;
            for(int i=0;i<people.size();i++){
                if(people.elementAt(i).getUserName().equals(username)){
                    if (people.get(i).getPassword().equals(password)){
                        flag=true;
                        System.out.println("Authentication is successful");
                        break;
                    }
                }
            }
            if(!flag) System.out.println("Wrong username or password");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
