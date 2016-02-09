package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.CuentaBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lliurex
 */
public class CuentaBancariaDAOImplHibernate extends GenericDAOImplHibernate<CuentaBancaria> implements CuentaBancariaDAO {

    @Override
    public List<CuentaBancaria> findBySucursal(int idSucursalBancaria) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Query query = session.createQuery("SELECT cuentabancaria FROM CuentaBancaria cuentabancaria WHERE idSucursalBancaria=?");
        query.setInteger(0, idSucursalBancaria);
        List<CuentaBancaria> cuentasBancarias = query.list();

        session.getTransaction().commit();
        return cuentasBancarias;
    }

    @Override
    public List<CuentaBancaria> findByUsuario(int idUsuario) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT cuentabancaria FROM CuentaBancaria cuentabancaria WHERE idUsuario=?");
        query.setInteger(0, idUsuario);

        List<CuentaBancaria> cuentasBancarias = query.list();

        session.getTransaction().commit();
        return cuentasBancarias;
    }

    @Override
    public CuentaBancaria getByNumeroCuenta(String numeroCuenta) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT cuentabancaria FROM CuentaBancaria cuentabancaria WHERE numeroCuenta=?");
        query.setString(0, numeroCuenta);
        
        List<CuentaBancaria> cuentaBancariaList = query.list();
        CuentaBancaria cuentaBancaria = cuentaBancariaList.get(0);
        
        session.getTransaction().commit();

        return cuentaBancaria;
    }

}
