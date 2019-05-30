package dao;

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
