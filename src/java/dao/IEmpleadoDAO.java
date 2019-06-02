package dao;

import entidades.Empleado;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface IEmpleadoDAO extends ICRUDDAO<Empleado> {

    @Override
    public int eliminar(int id);

    @Override
    public int actualizar(Empleado entity);

    @Override
    public int crear(Empleado entity);

    @Override
    public List<Empleado> getAll();

    @Override
    public Empleado getById(int id);
    
}
