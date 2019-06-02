package dao.fabrica;

import dao.IEncuestaDAO;
import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.impl.MySQLPeliculaDAO;
import dao.impl.MySQLGeneroDAO;
import dao.impl.MySQLEncuestaDAO;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MySQLDAOFabrica extends DAOFabrica {
    
    @Override
    public IPeliculaDAO getPeliculaDAO() {
        return new MySQLPeliculaDAO();
    }

    @Override
    public IGeneroDAO getGeneroDAO() {
        return new MySQLGeneroDAO();
    }

    @Override
    public IEncuestaDAO getEncuestaDAO() {
        return new MySQLEncuestaDAO();
    }
    
}
