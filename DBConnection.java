package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ersan on 30/03/2017.
 */
public class DBConnection {
    public final static String URL = "jdbc:mysql://35.161.234.133:3306/";
    public final static String DB_NAME = "Foosball system";
    public final static String USER = "Ersan";
    public final static String PASS = "pass";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    URL + DB_NAME,
                    USER,
                    PASS);
            return con;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}