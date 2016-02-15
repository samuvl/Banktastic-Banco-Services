package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.MovimientoBancarioDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lliurex
 */
public class MovimientoBancarioDAOImplHibernate extends GenericDAOImplHibernate<MovimientoBancario> implements MovimientoBancarioDAO {

    @Override
    public List<MovimientoBancario> getByIdCuenta(CuentaBancaria cuentaBancaria) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT movimientobancario FROM MovimientoBancario movimientobancario WHERE cuentaBancaria=?");
        query.setInteger(0, cuentaBancaria.getIdCuentaBancaria());

        List<MovimientoBancario> movimientosBancarios = query.list();
        session.getTransaction().commit();
        
        return movimientosBancarios;
    }

}
