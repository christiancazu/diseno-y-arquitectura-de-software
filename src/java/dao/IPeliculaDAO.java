package dao;

import entidades.Pelicula;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IPeliculaDAO extends ICRUDDAO<Pelicula> {

    @Override
    public Pelicula getById(int id) throws Exception;
    
    @Override
    public List<Pelicula> getAll() throws Exception;
    
    @Override
    public boolean crear(Pelicula entity) throws Exception;
    
    @Override
    public boolean actualizar(Pelicula entity) throws Exception;
    
    @Override
    public boolean eliminar(int id) throws Exception;
    
}
