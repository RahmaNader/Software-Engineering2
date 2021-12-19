import java.sql.*;

public class Rating {
    private static Statement stmt;

    public static void rateDriver(Person p, String username, double newRate) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        //data base
        String sql = "INSERT INTO RATING (USER,DRIVER,RATE) " +
                "VALUES (" + "'" + p.getUserName() + "'" + "," +
                "'" + username+ "'" + "," + newRate +");";
        stmt.executeUpdate(sql);
        DBConnection.closeConnection();
    }

    public static void viewAllRating(Person p) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        //data base
        String sql = "SELECT * FROM RATING WHERE DRIVER = '"+p.getUserName()+"';";
        ResultSet rs = stmt.executeQuery ( sql );
        System.out.println (rs.getDouble ( "Rate" ) );
        rs.close ();
        DBConnection.closeConnection();
    }

    public static void viewAvgRating(Person p, String in) throws SQLException {
        DBConnection.setupDbConnection("Rating");
        stmt = DBConnection.getStmt();
        // query to get avg rating
        //while loop
        //data base
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
