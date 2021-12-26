import java.sql.SQLException;

public class Request {


    static IDBRequest dbrequest = new DBRequest();

    static IEvent iEvent ;

    public static void requestRide(Person p, String source, String destination) {
        dbrequest.requestRide(p, source, destination);
    }

    public static void viewRequests(Person p){
        dbrequest.viewRequests(p);
    }

    public static void acceptRequest(int id){
        dbrequest.acceptRequest(id);
        try {
            iEvent.sendUserAccepts (id);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void cancelRequest(Person P, int id){
        dbrequest.cancelRequest(P, id);
    }

    public static void makeOffer(Person p, int id, int price){
        dbrequest.makeOffer(p, id, price);
        try {
            iEvent.sendCaptainOffer (p, id, price);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void captainArrivesLoc (int id)
    {
        // function to get ride id, and add this to "Event" database
        try {
            iEvent.sendCapArrivesLoc (id);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void captainArrivesDest (int id)
    {
        // function to get ride id, and add this to "Event" database
        try {
            iEvent.sendCapArrivesDest (id);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

}

