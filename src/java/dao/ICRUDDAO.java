package dao;

import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 * @param <T> entity type
 */
public interface ICRUDDAO<T> {

    T getById(int id);   

    List<T> getAll();
    
    boolean crear(T entity);
    
    boolean actualizar(T entity);

    boolean eliminar(int id);

}
