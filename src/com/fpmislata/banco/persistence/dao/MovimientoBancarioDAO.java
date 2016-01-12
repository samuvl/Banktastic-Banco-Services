package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author Lliurex
 */
public interface MovimientoBancarioDAO extends GenericDAO<MovimientoBancario>{
        List<MovimientoBancario> getByIdCuenta(int idCuentaBancaria) throws BusinessException;

}
