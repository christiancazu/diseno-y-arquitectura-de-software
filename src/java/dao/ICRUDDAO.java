package dao;

import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 * @param <T> entity type
 */
public interface ICRUDDAO<T> {

    T getById(int id) throws Exception;   

    List<T> getAll() throws Exception;
    
    boolean crear(T entity) throws Exception;
    
    boolean actualizar(T entity) throws Exception;

    boolean eliminar(int id) throws Exception;

}
