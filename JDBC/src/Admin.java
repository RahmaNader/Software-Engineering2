public class Admin {
    static Admin adminInstance;
    private Admin(){
    }
    public static Admin getInstance(){
        if(adminInstance == null) adminInstance = new Admin();
        return adminInstance;
    }
}
