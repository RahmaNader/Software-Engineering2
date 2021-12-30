import java.sql.SQLException;
import java.util.Date;

public interface IDBAdmin {
    public void listAllDriverRequests();
    public void listAllDrivers();
    public void listAllUsers();
    public void suspendDriver(String userName);
    public void suspendUser(String userName);
    public void activateDriver(String userName);
    public void activateUser(String userName);
    void displayEvent ( ) ;
    void displayEventByEventName (EventName eventName);
    void displayEventByDate ( Date date);
    void displayEventByUser (String user);
    void displayEventByDriver (String driver);
    void displayEventByRideID (int id);

}
