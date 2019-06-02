package dao;

import entidades.Genero;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IGeneroDAO extends ICRUDDAO<Genero> {

    @Override
    public Genero getById(int id) throws Exception;

    @Override
    public List<Genero> getAll() throws Exception;

    @Override
    public boolean crear(Genero entity) throws Exception;

    @Override
    public boolean actualizar(Genero entity) throws Exception;

    @Override
    public boolean eliminar(int id) throws Exception; 
        
}
