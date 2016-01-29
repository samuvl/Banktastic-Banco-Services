package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

public interface CuentaBancariaService extends GenericService<CuentaBancaria> {

    List<CuentaBancaria> findBySucursal(int idSucursalBancaria) throws BusinessException;
    List<CuentaBancaria> findByUsuario(int idUsuario) throws BusinessException;
    List<CuentaBancaria> findByDni(String dni) throws BusinessException;
    CuentaBancaria getByNumeroCuenta(String numeroCuenta) throws BusinessException;
    CuentaBancaria getByNumeroCuentaFull(String numeroCuentaFull) throws BusinessException;
    
}
