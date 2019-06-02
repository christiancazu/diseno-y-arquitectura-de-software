package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDBConexion {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/peliculas";
    private static final String USER= "root";
    private static final String PASSWORD = "";    

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
        }
    }

    public static Connection getConexion() {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return con;
    }
}
