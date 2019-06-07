package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDBConexion {

    protected Connection connection;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/peliculas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * getting MySQL connection
     *
     * @throws Exception
     */
    public void getConexion() throws Exception {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * closing MySQL connection if is opened
     *
     * @throws SQLException
     */
    public void closeConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
