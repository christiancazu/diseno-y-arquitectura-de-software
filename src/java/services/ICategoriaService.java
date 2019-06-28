package services;

import daoImpl.CategoriaDAO;
import entidades.Categoria;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface ICategoriaService extends IGenericService<Categoria> {

    @Override
    public List<Categoria> findAll();  
    
    @Override
    public Categoria findById(int id);
    
    @Override
    public boolean create(Categoria categoria);
    
    @Override
    public Categoria update(Categoria categoria);
    
    @Override
    public boolean deleteById(int id);     

}
