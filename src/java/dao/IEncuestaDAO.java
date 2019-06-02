package dao;

import entidades.Encuesta;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IEncuestaDAO extends ICRUDDAO<Encuesta> {

    @Override
    public boolean eliminar(int id);

    @Override
    public boolean actualizar(Encuesta entity);

    @Override
    public boolean crear(Encuesta entity);

    @Override
    public List<Encuesta> getAll();

    @Override
    public Encuesta getById(int id);
        
}
