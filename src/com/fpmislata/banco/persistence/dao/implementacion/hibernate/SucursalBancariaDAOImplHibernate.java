package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.SucursalBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class SucursalBancariaDAOImplHibernate extends GenericDAOImplHibernate<SucursalBancaria> implements SucursalBancariaDAO {

    @Override
    public List<SucursalBancaria> getByEntidad(int idEntidadBancaria) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT sucursalbancaria FROM SucursalBancaria sucursalbancaria WHERE idEntidadBancaria=?");
        query.setInteger(0, idEntidadBancaria);
        List<SucursalBancaria> sucursalesBancarias = query.list();

        return sucursalesBancarias;
    }

}
