import java.sql.SQLException;

public interface IEvent
{

    void sendUserAccepts (int id) throws SQLException;

    void sendCaptainOffer ( Person p , int id , int price ) throws SQLException;

    void sendCapArrivesLoc ( int id ) throws SQLException;

    void sendCapArrivesDest ( int id ) throws SQLException;


}
