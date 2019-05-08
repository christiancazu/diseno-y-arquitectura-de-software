package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDBConexion {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/colegio", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
