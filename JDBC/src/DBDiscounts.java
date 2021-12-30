import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBDiscounts implements IDBDiscounts
{

    private Statement stmt;
    public Double getAllDiscounts ( Date date , Rides ride ) throws SQLException {

        double discount = getDiscoundByDate ( date );
        discount += getDiscountByArea ( ride );
        return discount; // returns the total percentage of discounts that should be added
    }

    private Double getDiscoundByDate (Date date) throws SQLException {
        DBConnection.setupDbConnection("Discounts");
        stmt = DBConnection.getStmt();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM DISCOUNTS " +
                    "WHERE TIMES = " + "'" + date +"'" + ";");
        } catch (SQLException e) {
            e.printStackTrace ( );
        }

        double discount = 0;
        while (rs.next ())
        {
            discount += rs.getInt ( "discount" );
        }

        DBConnection.closeConnection();
        rs.close ();
        return discount;
    }

    private double getDiscountByArea ( Rides ride) throws SQLException {
        String area = ride.getDestination ( );
        DBConnection.setupDbConnection("Discounts");
        stmt = DBConnection.getStmt();
        ResultSet rs = stmt.executeQuery ( "SELECT AREA FROM main.DISCOUNTS WHERE AREA= '"+area+"';" );
        if (rs.getString ( "area" ) != null)
        {
            double discount = rs.getDouble ( "discount" );
            rs.close ();
            DBConnection.closeConnection ();
            return discount;
        }
        return 0;

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
