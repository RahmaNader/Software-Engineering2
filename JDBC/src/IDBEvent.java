import java.sql.SQLException;

public interface IDBEvent
{

    void displayEvent() throws SQLException;
    void addEvent (Event event);

}
