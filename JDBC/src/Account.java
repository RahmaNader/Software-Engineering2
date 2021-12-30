public class Account {
    
    static IDBAccount dbaccount = new DBAccount();

    public static void register(int in,String userName,String name,String mobile,String password,String email, String birthDate ,String natId ,String lic) {
        if(email.isBlank()) email = "null";
        if (in == 1){
            User user = new User(name, userName, mobile, email,birthDate, password);
            dbaccount.registerUser(user);
        } else if(in == 2){
            Driver driver = new Driver(name, userName, mobile, email, password, natId, lic);
            dbaccount.registerDriver(driver);
        }
        else System.out.println("Invalid Input\n=======================");
    }
    
    public static Driver loginDriver(String username, String password) {
        Driver p = dbaccount.loginDriver(username,password);
        return p;
    }
    
    public static User loginUser(String username, String password){
        User p  = dbaccount.loginUser(username, password);        
        return p;
    }
}
