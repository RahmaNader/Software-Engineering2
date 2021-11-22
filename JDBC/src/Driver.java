import java.util.Vector;

public class Driver extends Person {
    private String driverLicense;
    private String nationalID;
    private float avgRating;
    private Vector<String> favouriteAreas;

    public Driver() {
        favouriteAreas = new Vector<>();
    }

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
