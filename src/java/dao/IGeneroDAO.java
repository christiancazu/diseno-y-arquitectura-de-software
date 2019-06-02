package dao;

import entidades.Genero;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IGeneroDAO extends ICRUDDAO<Genero> {

    @Override
    public boolean eliminar(int id);

    @Override
    public boolean actualizar(Genero entity);

    @Override
    public boolean crear(Genero entity);

    @Override
    public List<Genero> getAll();

    @Override
    public Genero getById(int id);
        
}
