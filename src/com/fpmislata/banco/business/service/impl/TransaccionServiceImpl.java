

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


public class TransaccionServiceImpl implements TransaccionService{
    
    @Autowired
    MovimientoBancarioService movimientoBancarioService;
    
    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Override
    public void insertTransaccion(Transaccion transaccion) throws BusinessException{
            
            //Creo movimiento bancario con los datos que cojemos de transaccion
            
            MovimientoBancario OrigenMovimientoBancario = new MovimientoBancario();
            
            OrigenMovimientoBancario.setConcepto(transaccion.getConcepto());
            OrigenMovimientoBancario.setImporte(transaccion.getImporte());
            OrigenMovimientoBancario.setTipoMovimiento(RolMovimiento.debe);
            OrigenMovimientoBancario.setFecha(new Date());
            
            //obtengo la cuenta con el numero cuenta (para eso he tenido que crear el metodo getByNumeroCuenta()
            CuentaBancaria OrigenCuentaBancaria = cuentaBancariaService.getByNumeroCuenta(transaccion.getCuentaOrigen());
            
            //le paso la cuenta al movimiento, para que lo haga en esa cuenta
            OrigenMovimientoBancario.setCuentaBancaria(OrigenCuentaBancaria);
            
            //hago los calculos para restarle el importe al saldo y actualizo el saldo de la cuenta
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
            
            
            CuentaBancaria DestinoCuentaBancaria = cuentaBancariaService.getByNumeroCuenta(transaccion.getCuentaDestino());
                      
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
