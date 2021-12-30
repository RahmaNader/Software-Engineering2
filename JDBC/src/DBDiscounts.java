import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBDiscounts implements IDBDiscounts
{

    private Statement stmt;
    public Double getAllDiscounts ( Date date , int id ) throws SQLException {
        DBConnection.setupDbConnection("Discounts");
        stmt = DBConnection.getStmt();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM DISCOUNTS " +
                "WHERE TIMES = " + "'" + date +"'" + ";");

        double discount = 0;
        while (rs.next ())
        {
            discount += rs.getInt ( "discount" );
        }

        DBConnection.closeConnection();
        rs.close ();

        return discount; // returns the total percentage of discounts that should be added
    }

    public void addDiscountByDate ( Date date , double discount )
    {
        DBConnection.setupDbConnection("Discounts");
        stmt = DBConnection.getStmt();
        String sql = "INSERT INTO DISCOUNTS(TIMES , DISCOUNT)" +
                "VALUES (" +  "'" + date +"'" + "," + "'" + discount +"'" + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        DBConnection.closeConnection();
    }

    public void addDiscountByArea ( String area , double discount )
    {
        DBConnection.setupDbConnection("Discounts");
        stmt = DBConnection.getStmt();
        String sql = "INSERT INTO DISCOUNTS(AREA , DISCOUNT)" +
                "VALUES (" +  "'" + area +"'" + "," + "'" + discount +"'" + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        DBConnection.closeConnection();
    }


}
