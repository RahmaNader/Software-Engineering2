
public class Ride {
    private String src;
    private String dest;
    private double price;
    private User user;
    private CarDriver driver;
    private RideState rideState;

    Ride (){}
    Ride (String src, String dest, double price, User user, CarDriver driver )
    {
        this.src = src;
        this.dest = dest;
        this.price = price;
        this.user = user;
        this.driver = driver;
        rideState = RideState.Pending;
    }

    public RideState getRideState ( ) {
        return rideState;
    }

    public String getSrc ( ) {
        return src;
    }

    public void setSrc ( String src ) {
        this.src = src;
    }

    public String getDest ( ) {
        return dest;
    }

    public void setDest ( String dest ) {
        this.dest = dest;
    }

    public double getPrice ( ) {
        return price;
    }

    public void setPrice ( int price ) {
        this.price = price;
    }

    public User getUser ( ) {
        return user;
    }

    public void setUser ( User user ) {
        this.user = user;
    }

    public CarDriver getDriver ( ) {
        return driver;
    }

    public void setDriver ( CarDriver driver ) {
        if(driver != null) {
            rideState = RideState.Accepted;
            this.driver = driver;
        }
        else{
            rideState = RideState.Denied;
        }

    }


}
