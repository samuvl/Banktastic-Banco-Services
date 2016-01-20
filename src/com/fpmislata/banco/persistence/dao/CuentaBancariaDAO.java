package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author Lliurex
 */
public interface CuentaBancariaDAO extends GenericDAO<CuentaBancaria> {

    List<CuentaBancaria> findBySucursal(int idSucursalBancaria) throws BusinessException;
    List<CuentaBancaria> findByUsuario(int idUsuario) throws BusinessException;
}
