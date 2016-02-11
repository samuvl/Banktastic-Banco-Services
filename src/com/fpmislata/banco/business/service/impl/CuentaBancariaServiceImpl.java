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
import static java.lang.Integer.parseInt;
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

    @Override
    public CuentaBancaria getByNumeroCuenta(String numeroCuenta) throws BusinessException {
        return cuentaBancariaDAO.getByNumeroCuenta(numeroCuenta);
    }

    @Override
    public CuentaBancaria getByNumeroCuentaFull(String numeroCuentaFull) throws BusinessException {
        CuentaBancaria cuentaBancariaFinal;

        if (numeroCuentaFull.length() != 20) {
            throw new BusinessException("NumeroCuenta", "Debe de ser de 20 numeros");
        } else {
            try {
                cuentaBancariaFinal = cuentaBancariaDAO.getByNumeroCuenta(numeroCuentaFull.substring(10, 20));
            } catch (IndexOutOfBoundsException e) {
                throw new BusinessException("NumeroCuenta", "No existe");
            }
        }
        return cuentaBancariaFinal;
    }
}

//                /* Te ahorras todo esto*/ /*
//            String codigoEntidad = numeroCuentaFull.substring(0, 4);
//            String codigoSucursal = numeroCuentaFull.substring(4, 8);
//            String CDC = numeroCuentaFull.substring(8, 10);
//            String cuenta = numeroCuentaFull.substring(10, 20);
//
//            String calculoCDC = ControlDigitCalculator.calcularDC(codigoEntidad, codigoSucursal, cuenta);
//
//            if (!CDC.equalsIgnoreCase(calculoCDC)) {
//                throw new BusinessException("cdc", "cdc incorrecto");
//            } else {
/*
                //saco la entidad Bancaria
                EntidadBancaria entidadBancaria = entidadBancariaDAO.get(parseInt(numeroCuentaFull.substring(0, 4)));
                SucursalBancaria sucursalBancaria = sucursalBancariaDAO.getByCodigoSucursal(parseInt(numeroCuentaFull.substring(4, 8)));
                

                List<SucursalBancaria> listaSucursales = sucursalBancariaDAO.getByEntidad(entidadBancaria.getIdEntidadBancaria());

                for (SucursalBancaria sucursal : listaSucursales) {
                    //recorro la lista de sucursales y si coincide el codigoSucursal con la que tenemos en el numero de cuenta,
                    //saco la lista de cuentas

                    if (sucursal.getCodigoSucursalBancaria().equalsIgnoreCase(numeroCuentaFull.substring(4, 8))) {
                        //saco la sucursal segun codigo (este metodo es nuevo de las transacciones)
                        SucursalBancaria sucursalBancaria = sucursalBancariaDAO.getByCodigoSucursal(parseInt(numeroCuentaFull.substring(4, 8)));

                        //saco la lista de cuentas con el metodo findBySucursal(necesita como parametro el idSucursal obtenido anteriormente)
                        List<CuentaBancaria> listaCuentas = cuentaBancariaDAO.findBySucursal(sucursalBancaria.getIdSucursalBancaria());

                        for (CuentaBancaria cuentaBancaria : listaCuentas) {

                            //recorro la lista de cuentas y si coincide con el codigoCuenta, devuelvo finalmente el objeto CuentaBancaria
                            String cuentaString = String.valueOf(cuentaBancaria.getNumeroCuenta());

                            if (numeroCuentaFull.substring(10, 20).equalsIgnoreCase(cuentaString)) {

                                cuentaBancariaFinal = cuentaBancaria;

                                return cuentaBancariaFinal;

                            } else {
                            }
                        }
                    } else {
                    }
                }

                
            }

        }

//    }*/
