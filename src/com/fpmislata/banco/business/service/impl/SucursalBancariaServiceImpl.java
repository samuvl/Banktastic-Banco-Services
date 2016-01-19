package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.business.service.SucursalBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.SucursalBancariaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class SucursalBancariaServiceImpl extends GenericServiceImpl<SucursalBancaria> implements SucursalBancariaService{

    @Autowired
    SucursalBancariaDAO sucursalBancariaDAO;
    
    @Override
    public List<SucursalBancaria> getByEntidad(int idEntidadBancaria) throws BusinessException {
        return sucursalBancariaDAO.getByEntidad(idEntidadBancaria);
    }
    
}
