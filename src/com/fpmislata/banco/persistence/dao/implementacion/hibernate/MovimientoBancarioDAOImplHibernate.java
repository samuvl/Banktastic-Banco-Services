package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

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
    public List<MovimientoBancario> getByIdCuenta(int idCuentaBancaria) throws BusinessException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT movimientobancario FROM MovimientoBancario movimientobancario WHERE idCuentaBancaria=?");
        query.setInteger(0, idCuentaBancaria);
        
        List<MovimientoBancario> movimientosBancarios = query.list();

        session.close();
        return movimientosBancarios;
    }
    
}
