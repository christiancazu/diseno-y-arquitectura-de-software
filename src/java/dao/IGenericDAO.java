package dao;

import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 * @param <T>
 */
public interface IGenericDAO<T> {

    T getById(int id);   

    List<T> getAll();
    
    int crear(T entity);
    
    int actualizar(T entity);

    int eliminar(int id);

}
