public class Locations {
    public String location;
    public String user;
    public int ID;
    private static final RidesDB ridesDB = RidesDB.getInstance();

    public Locations() {
    }

    public Locations(String location, String user, int ID) {
        this.location = location;
        this.user = user;
        this.ID = ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}