public class Admin {

    public static Admin adminInstance;
    static IDBAdmin dbadmin = new DBAdmin();
    String username = "admin";
    String password = "admin";

    private Admin(){
        //admin
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static Admin getInstance(){
        if(adminInstance == null){
            adminInstance = new Admin();
        }
        return adminInstance;
    }

    public void listAllDriverRequests(){
        dbadmin.listAllDriverRequests();
    }

    public void listAllDrivers(){
        dbadmin.listAllDrivers();
    }

    public void listAllUsers(){
        dbadmin.listAllUsers();
    }

    public void suspendDriver(String userName){
        dbadmin.suspendDriver(userName);
    }

    public void suspendUser(String userName){
        dbadmin.suspendUser(userName);
    }

    public void activateDriver(String userName){
        dbadmin.activateDriver(userName);
    }

    public void activateUser(String userName){
        dbadmin.activateUser(userName);
    }

}