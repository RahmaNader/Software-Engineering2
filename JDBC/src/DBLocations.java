import java.sql.ResultSet;
import java.sql.Statement;

public class DBLocations implements IDBLocations{

    private Statement stmt;

    public void addFavourite(Person p, String in){
        DBConnection.setupDbConnection("Locations");
        stmt = DBConnection.getStmt();
        /// data base
        try {
            String sql = "INSERT INTO LOCATIONS (LOCATION,USERNAME) " +
                    "VALUES (" + "'" + in + "'" + "," + "'" + p.getUserName() + "'" + ");";
            stmt.executeUpdate(sql);
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Location added successfully");
    }

    public void displayFavorite(Person p){
        DBConnection.setupDbConnection("Locations");
        stmt = DBConnection.getStmt();
        try {
            System.out.println("Opened rides successfully");
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDES" +
                    "            INNER JOIN LOCATIONS" +
                    "            ON RIDES.SOURCE = LOCATIONS.LOCATION" +
                    "            WHERE LOCATIONS.USERNAME = '"+p.getUserName()+"' AND RIDES.STATUS = 'P';");
            while (rs.next()) {
                Print.rideData(rs);
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
