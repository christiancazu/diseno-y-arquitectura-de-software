package dao;

import entidades.Producto;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IProductoDAO extends IGenericDAO<Producto> {
    
    @Override
    public List<Producto> findAll();  
    
    @Override
    public Producto findById(int id);
    
    @Override
    public boolean create(Producto categoria);
    
    @Override
    public Producto update(Producto categoria);
    
    @Override
    public boolean deleteById(int id);

}
