public class Rides {
    private String source;
    private String destination;
    private String user;
    private String driver;
    private static int ID;
    private static RideStatus s;

    public int getID() {
        return ID;
    }

    public Rides() {
        s = RideStatus.Pending;
    }

    public Rides(String source, String destination, String user,int ID) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.ID = ID;
        s = RideStatus.Pending;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}

