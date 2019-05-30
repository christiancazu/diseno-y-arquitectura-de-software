package dao.fabrica;

import dao.impl.MySQLCursoDAO;
import dao.fabrica.DAOFabrica;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDAOFabrica extends DAOFabrica {
    
    @Override
    public MySQLCursoDAO getCursoDAO() {
        return new MySQLCursoDAO();
    }
}
