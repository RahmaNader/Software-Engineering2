import java.util.Date;

public interface IDBAccount {
    public void registerUser(User user);
    public void registerDriver(Driver driver);
    public User loginUser(String username, String password);
    public Driver loginDriver(String username, String password);
    public boolean checkBD ( Date date, String username);
}
