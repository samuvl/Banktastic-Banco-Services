package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class GenericDAOImplHibernate<T> implements GenericDAO<T> {

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        T t = (T) session.get(getEntityClass(), id);

        session.getTransaction().commit();
        session.close();

        return t;
    }

    @Override
    public T insert(T t) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            
            session.save(t);
            
        } catch (org.hibernate.exception.ConstraintViolationException ex) {

            SQLException sqlException = ex.getSQLException();
            if (sqlException.getErrorCode() == 1062 && sqlException.getSQLState().equals("23000")) {
                throw new BusinessException("valorDuplicado: ", "El valor está duplicado");
            } else {
                throw new RuntimeException(ex);
            }
        }

        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public T update(T t) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(t);

        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public boolean delete(int id) {
        boolean devolver;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        T t = this.get(id);

        if (t != null) {
            devolver = true;
            session.delete(t);
        } else {
            devolver = false;
        }

        session.getTransaction().commit();
        session.close();
        return devolver;
    }

    @Override
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        
        Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
        List<T> tes = query.list();

        return tes;
    }

}
