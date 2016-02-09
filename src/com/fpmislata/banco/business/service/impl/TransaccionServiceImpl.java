package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.business.domain.RolMovimiento;
import com.fpmislata.banco.business.domain.Transaccion;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.business.service.MovimientoBancarioService;
import com.fpmislata.banco.business.service.TransaccionService;
import com.fpmislata.banco.core.BusinessException;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    MovimientoBancarioService movimientoBancarioService;

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Override
    public void insertTransaccion(Transaccion transaccion) throws BusinessException {
        if (transaccion.getPin() != 2045) {
            throw new BusinessException("Pin", "Código pin Incorrecto.");
        }
        
        MovimientoBancario OrigenMovimientoBancario = new MovimientoBancario();
        OrigenMovimientoBancario.setConcepto(transaccion.getConcepto());
        OrigenMovimientoBancario.setImporte(transaccion.getImporte());
        OrigenMovimientoBancario.setTipoMovimiento(RolMovimiento.debe);
        OrigenMovimientoBancario.setFecha(new Date());

        //CCC (completo)
        String numeroCuentaOrigen = transaccion.getCuentaOrigen();
        CuentaBancaria OrigenCuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaOrigen);

        if (!OrigenCuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidad().equals(numeroCuentaOrigen.trim().substring(0, 4))) {
            throw new BusinessException("Código de Entidad", "Introduzca correctamente en Cuenta Origen.");
        }
        if (!OrigenCuentaBancaria.getSucursalBancaria().getCodigoSucursalBancaria().equals(numeroCuentaOrigen.trim().substring(4, 8))) {
            throw new BusinessException("Código Sucursal", "Introduzca correctamente en Cuenta Origen.");
        }
        if (!OrigenCuentaBancaria.getDigitoControl().equals(numeroCuentaOrigen.trim().substring(8, 10))) {
            throw new BusinessException("Digito de Control", "Introduzca correctamente en Cuenta Origen.");
        }
        //Número de Cuenta sólo
        if (!OrigenCuentaBancaria.getNumeroCuenta().equals(numeroCuentaOrigen.trim().substring(10, 20))) {
            throw new BusinessException("Número de Cuenta", "Introduzca correctamente en Cuenta Origen.");
        }
        OrigenMovimientoBancario.setCuentaBancaria(OrigenCuentaBancaria);

        
        BigDecimal importe = transaccion.getImporte();
        if (importe == null) {
            throw new BusinessException("Importe", "Debe indicar un Importe correcto.");
        } else if (importe.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Importe", "El importe no puede ser negativo.");
        }
        BigDecimal OrigenSaldoViejo = OrigenCuentaBancaria.getSaldo();
        OrigenMovimientoBancario.setSaldoAnterior(OrigenSaldoViejo);

        BigDecimal OrigenImporte = importe.multiply(new BigDecimal(-1));
        BigDecimal OrigenSaldoNuevo = OrigenSaldoViejo.add(OrigenImporte);
        OrigenMovimientoBancario.setSaldoPosterior(OrigenSaldoNuevo);

        if (OrigenSaldoNuevo.compareTo(BigDecimal.ZERO) > 0) {
            OrigenCuentaBancaria.setSaldo(OrigenSaldoNuevo);
            cuentaBancariaService.update(OrigenCuentaBancaria);
        } else {
            throw new BusinessException("Importe", "El importe indicado es superior al actual en la cuenta.");
        }
        
        movimientoBancarioService.insert(OrigenMovimientoBancario);

        /////////////////////////////////////////////////////Cuenta Destino
        MovimientoBancario DestinoMovimientoBancario = new MovimientoBancario();

        DestinoMovimientoBancario.setConcepto(transaccion.getConcepto());
        DestinoMovimientoBancario.setImporte(transaccion.getImporte());
        DestinoMovimientoBancario.setTipoMovimiento(RolMovimiento.haber);
        DestinoMovimientoBancario.setFecha(new Date());

        String numeroCuentaDestino = transaccion.getCuentaDestino();
        CuentaBancaria DestinoCuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaDestino);

        if (!DestinoCuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidad().equals(numeroCuentaDestino.trim().substring(0, 4))) {
            throw new BusinessException("Código de Entidad", "Introduzca correctamente en Cuenta Destino.");
        }
        if (!DestinoCuentaBancaria.getSucursalBancaria().getCodigoSucursalBancaria().equals(numeroCuentaDestino.trim().substring(4, 8))) {
            throw new BusinessException("Código Sucursal", "Introduzca correctamente en Cuenta Destino.");
        }
        if (!DestinoCuentaBancaria.getDigitoControl().equals(numeroCuentaDestino.trim().substring(8, 10))) {
            throw new BusinessException("Digito de Control", "Introduzca correctamente en Cuenta Destino.");
        }
        //Número de Cuenta sólo
        if (!DestinoCuentaBancaria.getNumeroCuenta().equals(numeroCuentaDestino.trim().substring(10, 20))) {
            throw new BusinessException("Número de Cuenta", "Introduzca correctamente en Cuenta Destino.");
        }

        DestinoMovimientoBancario.setCuentaBancaria(DestinoCuentaBancaria);

        BigDecimal DestinoSaldoViejo = DestinoCuentaBancaria.getSaldo();
        DestinoMovimientoBancario.setSaldoAnterior(DestinoSaldoViejo);

        BigDecimal DestinoSaldoNuevo = DestinoSaldoViejo.add(importe);
        DestinoMovimientoBancario.setSaldoPosterior(DestinoSaldoNuevo);

        DestinoCuentaBancaria.setSaldo(DestinoSaldoNuevo);

        cuentaBancariaService.update(DestinoCuentaBancaria);
        movimientoBancarioService.insert(DestinoMovimientoBancario);

    }

}
