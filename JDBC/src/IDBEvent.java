import java.sql.SQLException;
import java.util.Date;

public interface IDBEvent
{

    void displayEvent() throws SQLException;
    void addEvent (Event event);
    void displayEventByEventName (EventName eventName) throws SQLException;
    void displayEventByDate ( Date date) throws SQLException;
    void displayEventByUser (String user) throws SQLException;
    void displayEventByDriver (String driver) throws SQLException;
    void displayEventByRideID (int id) throws SQLException;

}
