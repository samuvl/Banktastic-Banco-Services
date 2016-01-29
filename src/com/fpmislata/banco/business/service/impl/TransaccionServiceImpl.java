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
        try{
        //recibiremos el numero completo que contiene codentidad+sucursal+digitoControl+numerocuenta
        //el formato de una cuenta es: 4 + 4 + 2+ 10            
        MovimientoBancario OrigenMovimientoBancario = new MovimientoBancario();

        OrigenMovimientoBancario.setConcepto(transaccion.getConcepto());
        OrigenMovimientoBancario.setImporte(transaccion.getImporte());
        OrigenMovimientoBancario.setTipoMovimiento(RolMovimiento.debe);
        OrigenMovimientoBancario.setFecha(new Date());

        String numeroCuentaOrigen = transaccion.getCuentaOrigen();

        CuentaBancaria OrigenCuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaOrigen);

        OrigenMovimientoBancario.setCuentaBancaria(OrigenCuentaBancaria);
        
        BigDecimal importe = transaccion.getImporte();
        BigDecimal OrigenSaldoViejo = OrigenCuentaBancaria.getSaldo();
        OrigenMovimientoBancario.setSaldoAnterior(OrigenSaldoViejo);

        BigDecimal OrigenImporte = importe.multiply(new BigDecimal(-1));  
        BigDecimal OrigenSaldoNuevo = OrigenSaldoViejo.add(OrigenImporte);           
        OrigenMovimientoBancario.setSaldoPosterior(OrigenSaldoNuevo);

        if (OrigenSaldoNuevo.compareTo(BigDecimal.ZERO) > 0) {
            OrigenCuentaBancaria.setSaldo(OrigenSaldoNuevo);
            cuentaBancariaService.update(OrigenCuentaBancaria);
        } else {
            throw new BusinessException("MovimientoBancario", "Ha ocurrido un error. NULL.");
        }

        //inserto movimiento
        movimientoBancarioService.insert(OrigenMovimientoBancario);

            //Cuenta Destino
        MovimientoBancario DestinoMovimientoBancario = new MovimientoBancario();

        DestinoMovimientoBancario.setConcepto(transaccion.getConcepto());
        DestinoMovimientoBancario.setImporte(transaccion.getImporte());
        DestinoMovimientoBancario.setTipoMovimiento(RolMovimiento.haber);
        DestinoMovimientoBancario.setFecha(new Date());

        String numeroCuentaDestino = transaccion.getCuentaDestino();

        CuentaBancaria DestinoCuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaDestino);

        DestinoMovimientoBancario.setCuentaBancaria(DestinoCuentaBancaria);

        BigDecimal DestinoSaldoViejo = DestinoCuentaBancaria.getSaldo();
        DestinoMovimientoBancario.setSaldoAnterior(DestinoSaldoViejo);

        BigDecimal DestinoSaldoNuevo = DestinoSaldoViejo.add(importe);
        DestinoMovimientoBancario.setSaldoPosterior(DestinoSaldoNuevo);

        DestinoCuentaBancaria.setSaldo(DestinoSaldoNuevo);

        cuentaBancariaService.update(DestinoCuentaBancaria);

        movimientoBancarioService.insert(DestinoMovimientoBancario);
        
        }catch(BusinessException ex){
            
        throw new BusinessException("transaccion","ha ocurrido un error y no se ha realizado la transaccion");
        }
        

    }

}
