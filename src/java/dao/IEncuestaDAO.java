package dao;

import entidades.Encuesta;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IEncuestaDAO extends ICRUDDAO<Encuesta> {

    @Override
    public Encuesta getById(int id) throws Exception;

    @Override
    public List<Encuesta> getAll() throws Exception;

    @Override
    public boolean crear(Encuesta entity) throws Exception;
    
    public boolean crearEncuestas(List<Encuesta> encuestas) throws Exception;

    @Override
    public boolean actualizar(Encuesta entity) throws Exception;

    @Override
    public boolean eliminar(int id) throws Exception; 
        
}
