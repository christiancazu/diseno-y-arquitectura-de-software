package dao.fabrica;

import dao.impl.MySQLCursoDAO;
import dao.impl.MySQLAlumnoDAO;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDAOFabrica extends DAOFabrica {
    
    @Override
    public MySQLCursoDAO getCursoDAO() {
        return new MySQLCursoDAO();
    }
    
    @Override
    public MySQLAlumnoDAO getAlumnoDAO() {
        return new MySQLAlumnoDAO();
    }
}
