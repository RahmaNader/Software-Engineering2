public class Person {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String userName;
    private String mobileNum;
    private String email;
    private String password;

    public Person() {
        userName = "null";
    }

    public Person(String userName, String name, String mobileNum, String password) {
        this.userName = userName;
        this.name = name;
        this.mobileNum = mobileNum;
        this.password = password;
    }

    public void login(String userName, String password) {

    }

    //setters and getters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //different signature with email
    //    public void register(String userName, String mobileNum, String email, String password){
//
//    }
}