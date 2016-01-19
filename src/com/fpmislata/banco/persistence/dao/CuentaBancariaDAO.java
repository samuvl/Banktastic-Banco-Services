package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author Lliurex
 */
public interface CuentaBancariaDAO extends GenericDAO<CuentaBancaria> {

    List<CuentaBancaria> getBySucursal(int idSucursalBancaria) throws BusinessException;
}
