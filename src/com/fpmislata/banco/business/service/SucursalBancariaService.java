package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

public interface SucursalBancariaService extends GenericService<SucursalBancaria> {

    List<SucursalBancaria> getByEntidad(int idEntidadBancaria) throws BusinessException;

}
