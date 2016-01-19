package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

public interface CuentaBancariaService extends GenericService<CuentaBancaria> {

    List<CuentaBancaria> getBySucursal(int idSucursalBancaria) throws BusinessException;
    List<CuentaBancaria> getByDni(String dni) throws BusinessException;

}
