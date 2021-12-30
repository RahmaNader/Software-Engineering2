import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public interface IDBRequest {
    public void requestRide( Person p,String source, String destination, LocalDate date,  int numOfPassengers);
    public void viewRequests(Person p);
    public void acceptRequest(int id);
    public void cancelRequest(Person P, int id);
    public void makeOffer(Person p, int id, int price);
    public int checkNumOfPassengers (int id, Date date);
    public boolean checkFirstRide (int id) throws SQLException;
}
