package dao;

import entidades.Boleta;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IBoletaDAO extends IGenericDAO<Boleta> {
    
    @Override
    public List<Boleta> findAll() throws Exception;  
    
    @Override
    public Boleta findById(int id);
    
    @Override
    public boolean create(Boleta categoria);
    
    @Override
    public Boleta update(Boleta categoria);
    
    @Override
    public boolean deleteById(int id);

}
