/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Alumno;
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
public class AlumnoDAO implements IAlumnoDAO {

    Session session;
    Transaction tx;

    public AlumnoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    @Override
    public Alumno getById(int id) {
        Alumno alumno = null;
        Query query = null;

        try {
            query = session.createQuery(
                    "from Alumno "
                    + "where id = ?");

            query.setParameter(0, id);
            alumno = (Alumno) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();

        return alumno;
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> alumnos = null;
        Query query = null;

        try {
            query = session.createQuery("from Alumno");

            alumnos = (List<Alumno>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();

        return alumnos;
    }

    @Override
    public List<Alumno> getAllByNameSurname(String cadena, String tipo) {
        List<Alumno> alumnos = null;
        Query query = null;

        try {
            if (tipo.equalsIgnoreCase("porNombres")) { //filtra por nombres            
                query = session.createQuery(
                        "from Alumno "
                        + "where nombre like ?");
            } else { //filtra por apellidos            
                query = session.createQuery(
                        "from Alumno "
                        + "where apellido like ?");
            }
            query.setParameter(0, "%" + cadena + "%");
            alumnos = (List<Alumno>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();

        return alumnos;
    }

    @Override
    public boolean store(Alumno alumno) {
        boolean success = false;

        try {
            session.saveOrUpdate(alumno);
            success = true;
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();

        return success;
    }

    @Override
    public Alumno update(int id, Alumno alumno) {
        alumno.setId(id);

        try {
            session.saveOrUpdate(alumno);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            alumno = null;
        }
        session.close();

        return alumno;
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        Query query = null;

        try {
            query = session.createQuery(
                    "delete from Alumno "
                    + "where id = ?");

            query.setParameter(0, id);
            result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();

        return result == 1;
    }

}
