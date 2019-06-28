package daoImpl;

import dao.ICategoriaDAO;
import entidades.Categoria;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class CategoriaDAO implements ICategoriaDAO {

    Session session;
    Transaction tx;

    public CategoriaDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categorias = null;
        Query query = null;

        try {
            query = session.createQuery("from Categoria");

            categorias = (List<Categoria>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
            return categorias;
        }
    }

    @Override
    public Categoria findById(int id) {
        Categoria categoria = null;

        try {
            categoria = (Categoria) session.get(Categoria.class, id);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
            return categoria;
        }
    }

    @Override
    public boolean create(Categoria categoria) {
        boolean created = false;

        try {
            session.saveOrUpdate(categoria);
            tx.commit();
            created = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
            return created;
        }
    }

    @Override
    public Categoria update(Categoria categoria) {
        try {
            session.update(categoria);
            tx.commit();       
        } catch (HibernateException e) {           
            tx.rollback();            
        } finally {
            session.close();
            return categoria;
        }
    }

    @Override
    public boolean deleteById(int id) {
        boolean deleted = false;

        try {
            Categoria categoria = (Categoria) session.get(Categoria.class, id);
            session.delete(categoria);
            tx.commit();
            deleted = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
            return deleted;
        }
    }

}
