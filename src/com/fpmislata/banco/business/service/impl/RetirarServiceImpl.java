package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.Extraccion;
import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.business.domain.RolMovimiento;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.business.service.MovimientoBancarioService;
import com.fpmislata.banco.business.service.RetirarService;
import com.fpmislata.banco.core.BusinessException;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

public class RetirarServiceImpl implements RetirarService {

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    MovimientoBancarioService movimientoBancarioService;

    @Override
    public void retirar(Extraccion extraccion) throws BusinessException {
        if (!extraccion.getToken().equals("2045")) {
            throw new BusinessException("Pin", "Retirar - Código pin Incorrecto." + extraccion.getToken());
        }

        //CCC (completo)
        String numeroCuentaCorrienteOrigen = extraccion.getCodigoCuentaCorriente();
        CuentaBancaria cuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaCorrienteOrigen);

        if (!cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidad().equals(numeroCuentaCorrienteOrigen.substring(0, 4))) {
            throw new BusinessException("Código de Entidad", "Introduzca correctamente en Cuenta Origen.");
        }
        if (!cuentaBancaria.getSucursalBancaria().getCodigoSucursalBancaria().equals(numeroCuentaCorrienteOrigen.substring(4, 8))) {
            throw new BusinessException("Código Sucursal", "Introduzca correctamente en Cuenta Origen.");
        }
        if (!cuentaBancaria.getDigitoControl().equals(numeroCuentaCorrienteOrigen.substring(8, 10))) {
            throw new BusinessException("Digito de Control", "Introduzca correctamente en Cuenta Origen.");
        }
        //Número de Cuenta sólo
        if (!cuentaBancaria.getNumeroCuenta().equals(numeroCuentaCorrienteOrigen.substring(10, 20))) {
            throw new BusinessException("Número de Cuenta", "Introduzca correctamente en Cuenta Origen.");
        }

        MovimientoBancario OrigenMovimientoBancario = new MovimientoBancario(cuentaBancaria, extraccion.getConcepto(), extraccion.getImporte(), new Date());
        OrigenMovimientoBancario.setTipoMovimiento(RolMovimiento.debe);
        BigDecimal importe = extraccion.getImporte();
        if (importe == null) {
            throw new BusinessException("Importe", "Debe indicar un Importe correcto.");
        } else if (importe.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Importe", "El importe no puede ser negativo.");
        }
        BigDecimal OrigenSaldoViejo = cuentaBancaria.getSaldo();
        OrigenMovimientoBancario.setSaldoAnterior(OrigenSaldoViejo);
        BigDecimal OrigenImporte = importe.multiply(new BigDecimal(-1));
        BigDecimal OrigenSaldoNuevo = OrigenSaldoViejo.add(OrigenImporte);
        OrigenMovimientoBancario.setSaldoPosterior(OrigenSaldoNuevo);
        if (OrigenSaldoNuevo.compareTo(BigDecimal.ZERO) > 0) {
            cuentaBancaria.setSaldo(OrigenSaldoNuevo);
            cuentaBancariaService.update(cuentaBancaria);
        } else {
            throw new BusinessException("Importe", "El importe indicado es superior al actual en la cuenta.");
        }

        movimientoBancarioService.insert(OrigenMovimientoBancario);
        System.out.println("TERMINA en retirar");
    }

}
