package dao;

import entidades.Curso;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface ICursoDAO {
  
    public List<Curso> listarCursos();
    
    public int insertarCurso(Curso curso);
}
