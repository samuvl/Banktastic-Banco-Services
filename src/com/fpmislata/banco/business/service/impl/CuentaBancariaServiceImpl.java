package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.CuentaBancariaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CuentaBancariaServiceImpl extends GenericServiceImpl<CuentaBancaria> implements CuentaBancariaService {

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;

    @Override
    public List<CuentaBancaria> getBySucursal(int idSucursalBancaria) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
