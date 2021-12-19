public class Driver extends Person {
    private String driverLicense;
    
    private String nationalID;
    
    private float avgRating;
    
    public Driver() {}
    
    public Driver(String name, String userName, String mobile, String email, String password, String nationalID, String driverLicense) {
        super(name,userName,mobile,email,password);
        this.nationalID = nationalID;
        this.driverLicense = driverLicense;
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