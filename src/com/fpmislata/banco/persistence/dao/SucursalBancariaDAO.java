package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

public interface SucursalBancariaDAO extends GenericDAO<SucursalBancaria> {

    List<SucursalBancaria> getByEntidad(int idEntidadBancaria) throws BusinessException;

}
