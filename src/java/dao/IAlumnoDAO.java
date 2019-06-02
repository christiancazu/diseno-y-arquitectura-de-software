package dao;

import entidades.Alumno;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IAlumnoDAO extends ICRUDDAO<Alumno> {

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Alumno entity);

    @Override
    public int crear(Alumno entity);

    @Override
    public List<Alumno> getAll();

    @Override
    public Alumno getById(int id);   

}
