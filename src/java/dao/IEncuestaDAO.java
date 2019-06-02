package dao;

import entidades.Encuesta;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IEncuestaDAO extends ICRUDDAO<Encuesta> {

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Encuesta entity);

    @Override
    public int crear(Encuesta entity);

    @Override
    public List<Encuesta> getAll();

    @Override
    public Encuesta getById(int id);
        
}
