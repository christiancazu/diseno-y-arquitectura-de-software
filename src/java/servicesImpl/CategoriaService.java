package servicesImpl;

import daoImpl.CategoriaDAO;
import entidades.Categoria;
import java.util.List;
import services.ICategoriaService;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class CategoriaService implements ICategoriaService {
    
    private CategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria findById(int id) {
        return categoriaDAO.findById(id);
    }

    @Override
    public boolean create(Categoria categoria) {
        return categoriaDAO.create(categoria);
    }

    @Override
    public Categoria update(Categoria categoria) {
        return categoriaDAO.update(categoria);
    }

    @Override
    public boolean deleteById(int id) {
        return categoriaDAO.deleteById(id);
    }

}
