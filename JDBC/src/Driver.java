import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class Driver extends Person {
    private String driverLicense;
    private String nationalID;
    private float avgRating;
    private Vector<String> favouriteAreas;
    private Map<String, Double> usersRatings; // to save each user's rating

    public Driver() {
        favouriteAreas = new Vector<>();
    }

    // rate and the username of the user who gave him that rating
    public void addRating (double rate, String username)
    {
        usersRatings.put (username,rate);
        avgRating = (float) ((avgRating + rate) / 2);
    }

    public void getAllRatings ()
    {
        System.out.println ("All your ratings: " );
        System.out.println (usersRatings.toString () );

    }

    // either in database or here-> should print average rating
//    public void displayInformation ()
//    {
//        System.out.println ( );
//    }

    public void addArea(String area) {

    }

    public void listRides() {

    }

    public void offerPrice() {

    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }
}
