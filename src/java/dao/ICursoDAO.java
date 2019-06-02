package dao;

import entidades.Curso;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface ICursoDAO extends ICRUDDAO<Curso>{

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Curso entity);

    @Override
    public int crear(Curso entity);

    @Override
    public List<Curso> getAll();

    @Override
    public Curso getById(int id);
    
}
