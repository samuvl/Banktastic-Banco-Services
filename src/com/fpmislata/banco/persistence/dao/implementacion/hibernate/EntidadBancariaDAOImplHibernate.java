package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.persistence.dao.EntidadBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EntidadBancariaDAOImplHibernate extends GenericDAOImplHibernate<EntidadBancaria> implements EntidadBancariaDAO {

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT entidadBancaria FROM EntidadBancaria entidadBancaria WHERE nombre=?");
        query.setString(0, nombre);
        List<EntidadBancaria> entidadesBancarias = query.list();

        return entidadesBancarias;
    }
}