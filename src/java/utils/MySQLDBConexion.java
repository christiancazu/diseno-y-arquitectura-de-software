/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDBConexion {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/instituto";
    public static final String DBUSER= "root";
    public static final String DBPASSWORD = "";    

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
        }
    }

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (SQLException e) {
        }
        return con;
    }

}
