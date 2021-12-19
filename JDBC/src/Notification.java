import java.sql.*;

public class Notification {
    private static Statement stmt;
    private static Statement stmt1;
    private static Connection connection;
    public void setUpNotification(){
        DBConnection.setupDbConnection("Notification");
        stmt = DBConnection.getStmt();
        try{
            Class.forName("Notification");
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            stmt1 = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES" +
                    "            INNER JOIN LOCATIONS" +
                    "            ON RIDES.SOURCE = LOCATIONS.LOCATION;");
            String sql;
            while (rs.next()) {
                sql = "INSERT OR IGNORE INTO NOTIFICATION (SOURCE,DESTINATION,DRIVER)" +
                        "VALUES(' "+rs.getString("source")+"','"+rs.getString("destination")+"','"+rs.getString("username")+"');";
                stmt1.executeUpdate(sql);
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
