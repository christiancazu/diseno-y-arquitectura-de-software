package dao.fabrica;

import dao.IAlumnoDAO;
import dao.ICursoDAO;
import dao.IEmpleadoDAO;
import dao.impl.MySQLCursoDAO;
import dao.impl.MySQLAlumnoDAO;
import dao.impl.MySQLEmpleadoDAO;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDAOFabrica extends DAOFabrica {
    
    @Override
    public ICursoDAO getCursoDAO() {
        return new MySQLCursoDAO();
    }
    
    @Override
    public IAlumnoDAO getAlumnoDAO() {
        return new MySQLAlumnoDAO();
    }

    @Override
    public IEmpleadoDAO getEmpleadoDAO() {
        return new MySQLEmpleadoDAO();
    }
    
}
