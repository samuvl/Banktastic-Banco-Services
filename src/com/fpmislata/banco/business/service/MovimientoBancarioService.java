package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

public interface MovimientoBancarioService extends GenericService<MovimientoBancario> {

    List<MovimientoBancario> getByIdCuenta(CuentaBancaria cuentaBancaria) throws BusinessException;
}
