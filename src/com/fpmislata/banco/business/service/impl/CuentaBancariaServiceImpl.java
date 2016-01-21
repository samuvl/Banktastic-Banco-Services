package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.ControlDigitCalculator;
import com.fpmislata.banco.persistence.dao.CuentaBancariaDAO;
import com.fpmislata.banco.persistence.dao.EntidadBancariaDAO;
import com.fpmislata.banco.persistence.dao.SucursalBancariaDAO;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CuentaBancariaServiceImpl extends GenericServiceImpl<CuentaBancaria> implements CuentaBancariaService {

    @Autowired
    SucursalBancariaDAO sucursalBancariaDAO;

    @Autowired
    EntidadBancariaDAO entidadBancariaDAO;

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public CuentaBancaria insert(CuentaBancaria cuentaBancaria) throws BusinessException {
        if (cuentaBancaria.getSucursalBancaria() == null) {
            throw new BusinessException("Sucursal", "No puede estar vacío.");
        } else {
            SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getIdSucursalBancaria());
            EntidadBancaria entidadBancaria = entidadBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getIdEntidadBancaria());

            if (cuentaBancaria.getNumeroCuenta() == null || cuentaBancaria.getNumeroCuenta().length() != 10) {
                throw new BusinessException("Numero Cuenta", "Introduzca 10 dígitos.");
            } else {
                String digitoControl = ControlDigitCalculator.calcularDC(entidadBancaria.getCodigoEntidad(), sucursalBancaria.getCodigoSucursalBancaria(), cuentaBancaria.getNumeroCuenta());
                cuentaBancaria.setDigitoControl(digitoControl);

                if (cuentaBancaria.getSaldo() == null) {
                    BigDecimal bigDecimal = new BigDecimal("0.00");
                    cuentaBancaria.setSaldo(bigDecimal);
                }
            }
        }
        return genericDAO.insert(cuentaBancaria);

    }

    @Override
    public CuentaBancaria update(CuentaBancaria cuentaBancaria) throws BusinessException {
        if (cuentaBancaria.getSucursalBancaria() == null) {
            throw new BusinessException("Sucursal", "No puede estar vacío.");
        } else {
            SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getIdSucursalBancaria());
            EntidadBancaria entidadBancaria = entidadBancariaDAO.get(cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getIdEntidadBancaria());

            if (cuentaBancaria.getNumeroCuenta() == null || cuentaBancaria.getNumeroCuenta().length() != 10) {
                throw new BusinessException("Numero Cuenta", "Introduzca 10 dígitos.");
            } else {
                String digitoControl = ControlDigitCalculator.calcularDC(entidadBancaria.getCodigoEntidad(), sucursalBancaria.getCodigoSucursalBancaria(), cuentaBancaria.getNumeroCuenta());
                cuentaBancaria.setDigitoControl(digitoControl);
            }
        }
        return genericDAO.update(cuentaBancaria);

    }

    @Override
    public List<CuentaBancaria> findBySucursal(int idSucursalBancaria) throws BusinessException {
        return cuentaBancariaDAO.findBySucursal(idSucursalBancaria);
    }

    @Override
    public List<CuentaBancaria> findByUsuario(int idUsuario) throws BusinessException {
        return cuentaBancariaDAO.findByUsuario(idUsuario);
    }

    @Override
    public List<CuentaBancaria> findByDni(String dni) throws BusinessException {
        Usuario usuario = usuarioDAO.getByDni(dni);
        int idUsuario = usuario.getIdUsuario();

        return cuentaBancariaDAO.findByUsuario(idUsuario);
    }

}
