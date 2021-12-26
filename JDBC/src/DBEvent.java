import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;

public class DBEvent implements IDBEvent
{
    public Statement stmt;


    // to print events by ride id or by category or all

    public void displayEvent ( ) throws SQLException {
        //  access database and print

        DBConnection.setupDbConnection("DBEvent");
        stmt = DBConnection.getStmt();

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM EVENT ");
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        while (rs.next ())
        {

            System.out.println (rs.getString ( "ID" ) );
            System.out.println (rs.getString ( "RIDEID" ) );
            System.out.println (rs.getString ( "EVENTNAME" ) );

            System.out.println (rs.getString ( "DRIVER" ) );
            System.out.println (rs.getString ( "USER" ) );
            System.out.println (rs.getString ( "TIME" ) );
            System.out.println (rs.getDouble ( "PRICE" ) );

        }

        System.out.println ("End of Event entry! " );
        rs.close();
        DBConnection.closeConnection();

    }

    public void addEvent (Event event)
    {
        DBConnection.setupDbConnection("DBEvent");
        stmt = DBConnection.getStmt();

        String sql =
                "INSERT INTO EVENT (RIDEID , USER , DRIVER , EVENTNAME , TIME)" +
                        "VALUES ("+ "'" + event.getRideID()+ "'" +","+
                        "'" + event.getUser() +"'"+"," +"'" + event.getDriver() + "'" +","+
                        "'" + event.getEventName() + "'" + "," + "'" + event.getTime() + "'"
                        +");"
                ; // INSERTION CODE

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        DBConnection.closeConnection();
        System.out.println("Event created successfully");


    }

}
