public class Locations {

    static IDBLocations dblocations = new DBLocations();

    public String location;
    
    public String user;
    
    public int ID;
    
    public static void addFavourite(Person p, String in) {
        dblocations.addFavourite(p,in);
    }
    
    public static void displayFavorite(Person p){
        dblocations.displayFavorite(p);
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