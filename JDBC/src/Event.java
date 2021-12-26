import java.sql.SQLException;
import java.time.LocalDateTime;

enum EventName
{
    captainOffer, UserAccept , capArrivesLoc ,capArrivesDest , noEvent
}

/*
        Note that : While printing should print in "user accepts" and "captain offers"
        different things! -> see project description !
        +
        If want to save the price in other points more than "captain offers"
        could make extra function for getting it from rides database
 */

public class Event implements IEvent
{

    static IDBEvent dbevent = new DBEvent ();


    private LocalDateTime time;
    private String userName, captainName;
    private EventName eventName = EventName.noEvent;
    private int rideId;
    private double price;


    public void sendUserAccepts ( int id ) throws SQLException {
//        addUserAccept ( id );
        Event event = new Event ();
        Rides ride = Rides.getRideByID ( id );
        this.rideId = id;
        this.captainName = ride.getDriver ();
        this.eventName = event.eventName = EventName.UserAccept;
        this.userName = event.userName = ride.getUser () ; // to get the username from the table ride
        this.time = event.time =  LocalDateTime.now (); ;
        dbevent.addEvent ( event );

    }

    public void sendCapArrivesDest ( int id ) throws SQLException {
        Event event = new Event ();
        Rides ride = Rides.getRideByID ( id );
        this.rideId = id;
        this.captainName = ride.getDriver ();
        this.eventName = event.eventName = EventName.capArrivesDest;
        this.userName = event.userName = ride.getUser () ; // to get the username from the table ride
        this.time = event.time =  LocalDateTime.now (); ;
        dbevent.addEvent ( event );

    }

    public void sendCapArrivesLoc ( int id ) throws SQLException {
        Event event = new Event ();
        Rides ride = Rides.getRideByID ( id );
        this.rideId = id;
        this.captainName = ride.getDriver ();
        this.eventName = event.eventName = EventName.capArrivesLoc;
        this.userName = event.userName = ride.getUser () ; // to get the username from the table ride
        this.time = event.time =  LocalDateTime.now (); ;
        dbevent.addEvent ( event );
    }

    public void sendCaptainOffer ( Person p , int id , int price ) throws SQLException {
        Event event = new Event ();
        Rides ride = Rides.getRideByID ( id );
        this.rideId = id;
        this.price = price ;
        this.captainName = p.getUserName ();
        this.eventName = event.eventName = EventName.captainOffer;
        this.userName = event.userName = ride.getUser () ; // to get the username from the table ride
        this.time = event.time =  LocalDateTime.now (); ;
        dbevent.addEvent ( event );
    }



    public int getRideID ( ) {
        return rideId;
    }

    public String getUser ( ) {
        return userName;
    }

    public EventName getEventName ( ) {
        return eventName;
    }

    public String getDriver ( ) {
        return captainName;
    }

    public LocalDateTime getTime ( ) {
        return time;
    }


    public double getPrice ( ) {
        return price;
    }
}
