package dao;

import entidades.Pelicula;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IPeliculaDAO extends ICRUDDAO<Pelicula> {

    @Override
    public boolean eliminar(int id);

    @Override
    public boolean actualizar(Pelicula entity);

    @Override
    public boolean crear(Pelicula entity);

    @Override
    public List<Pelicula> getAll();

    @Override
    public Pelicula getById(int id);
    
}
