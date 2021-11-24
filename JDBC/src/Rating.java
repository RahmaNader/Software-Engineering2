import java.sql.*;
import java.util.Scanner;

public class Rating {
    private static Statement stmt;
    private static final Scanner input = new Scanner(System.in);

    public static void rateDriver(Person p) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        System.out.print ("Enter the username: " );
        String username = input.nextLine ();
        System.out.print ("Enter the rating from 1 to 5 (1 worst, 5 best) :" );
        double newRate = input.nextDouble ();
        String sql = "INSERT INTO RATING (USER,DRIVER,RATE) " +
                "VALUES (" + "'" + p.getUserName() + "'" + "," +
                "'" + username+ "'" + "," + newRate +");";
        stmt.executeUpdate(sql);
        DBConnection.closeConnection();
    }
    public static void viewAllRating(Person p) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        String sql = "SELECT * FROM RATING WHERE DRIVER = '"+p.getUserName()+"';";
        ResultSet rs = stmt.executeQuery ( sql );
        System.out.println (rs.getDouble ( "Rate" ) );
        rs.close ();
        DBConnection.closeConnection();
    }
    public static void viewAvgRating(Person p) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        // query to get avg rating
        System.out.println("Enter driver user name");
        String in = input.nextLine();
        //while loop
        String sql = "SELECT RATE FROM RATING WHERE DRIVER ='"+in+"';";
        ResultSet rs = stmt.executeQuery (sql);
        double avg = 0; int total = 1;
        while (rs.next()) {
            avg += rs.getDouble("rate");
            avg /= total;
            total++;
        }
        System.out.println("Average rating for selected driver : "+avg);
        rs.close ();
        DBConnection.closeConnection();
    }
}
