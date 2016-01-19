package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.ControlDigitCalculator;
import com.fpmislata.banco.persistence.dao.CuentaBancariaDAO;
import com.fpmislata.banco.persistence.dao.EntidadBancariaDAO;
import com.fpmislata.banco.persistence.dao.SucursalBancariaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CuentaBancariaServiceImpl extends GenericServiceImpl<CuentaBancaria> implements CuentaBancariaService {

    @Autowired
    SucursalBancariaDAO sucursalBancariaDAO;
    
    @Autowired
    EntidadBancariaDAO entidadBancariaDAO;
    
    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;
    
    
    @Override
    public CuentaBancaria insert(CuentaBancaria cuentaBancaria) throws BusinessException {
        SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getIdSucursalBancaria());
        EntidadBancaria entidadBancaria = entidadBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getIdEntidadBancaria());

        String digitoControl = ControlDigitCalculator.calcularDC(entidadBancaria.getCodigoEntidad(), sucursalBancaria.getCodigoSucursalBancaria(), cuentaBancaria.getNumeroCuenta());
        cuentaBancaria.setDigitoControl(digitoControl);

        return genericDAO.insert(cuentaBancaria);
    }

    @Override
    public CuentaBancaria update(CuentaBancaria cuentaBancaria) throws BusinessException {
        SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getIdSucursalBancaria());
        EntidadBancaria entidadBancaria = entidadBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getIdEntidadBancaria());

        String digitoControl = ControlDigitCalculator.calcularDC(entidadBancaria.getCodigoEntidad(), sucursalBancaria.getCodigoSucursalBancaria(), cuentaBancaria.getNumeroCuenta());
        cuentaBancaria.setDigitoControl(digitoControl);

        return genericDAO.update(cuentaBancaria);
    }

    @Override
    public List<CuentaBancaria> getBySucursal(int idSucursalBancaria) throws BusinessException {
        return cuentaBancariaDAO.getBySucursal(idSucursalBancaria);
    }

    @Override
    public List<CuentaBancaria> getByUsuario(int idUsuario) throws BusinessException {
        return cuentaBancariaDAO.getByUsuario(idUsuario);
    }
}
