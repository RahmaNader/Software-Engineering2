import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class DBConnection {
    private static Statement stmt;
    private static Connection connection;

    DBConnection(){
        connection = null;
        stmt = null;
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setupDbConnection(String className){
        try{
            Class.forName(className);
            connection = DriverManager.getConnection("jdbc:sqlite:database");
            stmt = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
