import java.sql.ResultSet;
import java.sql.SQLException;

public class Print {
    public static void driverData(ResultSet rs) throws SQLException {
        System.out.println();
        System.out.println();
        System.out.println("USER NAME = " + rs.getString("username"));
        System.out.println("NAME = " + rs.getString("name"));
        System.out.println("MOBILE = " + rs.getString("mobile"));
        System.out.println("PASSWORD = " + rs.getString("password"));
        System.out.println("STATUS = " + rs.getString("status"));
        System.out.println("EMAIL = " + rs.getString("email"));
        System.out.println("NATIONAL ID = " + rs.getString("nationalid"));
        System.out.println("LICENSE = " + rs.getString("license"));
        System.out.println();
        System.out.println();
    }
    public static void userData(ResultSet rs)throws SQLException{
        System.out.println();
        System.out.println("USER NAME = " + rs.getString("username"));
        System.out.println("NAME = " + rs.getString("name"));
        System.out.println("MOBILE = " + rs.getString("mobile"));
        System.out.println("PASSWORD = " + rs.getString("password"));
        System.out.println("STATUS = "+rs.getString("status"));
        System.out.println("EMAIL = "+rs.getString("email"));
        System.out.println();
    }
    public static void rideData(ResultSet rs)throws SQLException{
        System.out.println();
        System.out.println("ID = " + rs.getString("id"));
        System.out.println("SOURCE = " + rs.getString("source"));
        System.out.println("DESTINATION = " + rs.getString("destination"));
        System.out.println("DRIVER = " + rs.getString("driver"));
        System.out.println("PRICE = " + rs.getString("price"));
        System.out.println("STATUS = " + rs.getString("status"));
        System.out.println("////////////////////");
    }
}
