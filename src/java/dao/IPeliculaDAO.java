package dao;

import entidades.Pelicula;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IPeliculaDAO extends ICRUDDAO<Pelicula> {

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Pelicula entity);

    @Override
    public int crear(Pelicula entity);

    @Override
    public List<Pelicula> getAll();

    @Override
    public Pelicula getById(int id);
    
}
