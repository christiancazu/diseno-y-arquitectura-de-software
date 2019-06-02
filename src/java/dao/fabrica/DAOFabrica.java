package dao.fabrica;

import dao.IPeliculaDAO;
import dao.IGeneroDAO;
import dao.IEncuestaDAO;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public abstract class DAOFabrica {

    // List of DAO types supported by the factory
    public static final int MYSQL = 1;
    public static final int SQL_SERVER = 2;
    public static final int ORACLE = 3;

    // There will be a method for each DAO that can be created. 
    // The concrete factories will have to implement these methods.
    public abstract IPeliculaDAO getPeliculaDAO();
    public abstract IGeneroDAO getGeneroDAO();
    public abstract IEncuestaDAO getEncuestaDAO();

    // There will going to return subFabrica according to the type
    public static DAOFabrica getDAOFabrica(int tipo) {        
        switch (tipo) {
            case MYSQL:
                return new MySQLDAOFabrica();
            case SQL_SERVER:
            // The SqlServerDAOFabrica is created when needed with SqlServer
            // return new SqlServerDAOFabrica();
            case ORACLE:
            // The OracleDAOFabrica is created when needed with Oracle
            // return new OracleDAOFabrica();
            default:
                return null;
        }
    }
}
