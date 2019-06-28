package services;

import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 * @param <T>
 */
public interface IGenericService<T> {
    
    public List<T> findAll() throws Exception;
    
    public T findById(int id) throws Exception;

    public boolean create(T entity) throws Exception;    

    public T update(T entity) throws Exception;

    public boolean deleteById(int id) throws Exception;

}
