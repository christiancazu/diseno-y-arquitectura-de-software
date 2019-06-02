package dao;

import entidades.Genero;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IGeneroDAO extends ICRUDDAO<Genero> {

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Genero entity);

    @Override
    public int crear(Genero entity);

    @Override
    public List<Genero> getAll();

    @Override
    public Genero getById(int id);
        
}
