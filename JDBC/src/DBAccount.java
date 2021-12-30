import org.sqlite.core.DB;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccount implements IDBAccount{
    
    private Statement stmt;

    public void registerUser(User user){
        DBConnection.setupDbConnection("DBAccount");
        stmt = DBConnection.getStmt();
        try{
            String sql = "INSERT INTO USER (USER_NAME,NAME,MOBILE,PASSWORD,EMAIL, BIRTHDATE) " +
                    "VALUES (" + "'" + user.getUserName() + "'" + "," +
                    "'" + user.getName() + "'" + "," +
                    "'" + user.getMobileNum() + "'" + "," +
                    "'" + user.getPassword() + "'" + "," +
                    "'" + user.getEmail() + "'" +  "," +
                    "'" + user.getBirthDate ()+"'" + ");";
            stmt.execute(sql);
            DBConnection.closeConnection();
            System.out.println("User created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBD ( java.util.Date date, String username)
    {

        DBConnection.setupDbConnection("DBAccount");
        stmt = DBConnection.getStmt();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery ( "SELECT BIRTHDATE FROM USER WHERE USER_NAME = "+"'"+username+"'"+";" );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        Date birthDate = null;
        try {
            birthDate = rs.getDate ( "birthdate" );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        try {
            rs.close ();
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
        DBConnection.closeConnection ();
        return birthDate == date;

    }

    public void registerDriver(Driver driver){
        DBConnection.setupDbConnection("DBAccount");
        stmt = DBConnection.getStmt();
        try{
            String sql = "INSERT INTO DRIVER (USERNAME,NAME,MOBILE,PASSWORD,EMAIL,NATIONALID,LICENSE) " +
                    "VALUES (" + "'" + driver.getUserName() + "'" + "," +
                    "'" + driver.getName() + "'" + "," +
                    "'" + driver.getMobileNum() + "'" + "," +
                    "'" + driver.getPassword() + "'" + "," +
                    "'" + driver.getEmail()+ "'" + "," +
                    "'" + driver.getNationalID()+ "'" + "," +
                    "'" + driver.getDriverLicense() + "'" + ");";
            stmt.executeUpdate(sql);
            DBConnection.closeConnection();
            System.out.println("Driver created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User loginUser(String username, String password){
        User p  = new User();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = '"+username+"'  " +
                    "AND PASSWORD = '"+password+"' AND STATUS = 'A';");
            while (rs.next()) {
                System.out.println();
                p.setUserName(rs.getString("username"));
                p.setUserName(rs.getString("name"));
                p.setMobileNum(rs.getString("mobile"));
                p.setPassword(rs.getString("password"));
                System.out.println("///////////////////////////////");
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Driver loginDriver(String username, String password){
        Driver p = new Driver();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER WHERE USERNAME = '"+username+"'  " +
                    "AND PASSWORD = '"+password+"' AND STATUS = 'A';");
            while (rs.next()) {
                System.out.println();
                p.setUserName(rs.getString("username"));
                p.setUserName(rs.getString("name"));
                p.setMobileNum(rs.getString("mobile"));
                p.setPassword(rs.getString("password"));
                System.out.println("///////////////////////////////");
            }
            rs.close();
            DBConnection.closeConnection();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
