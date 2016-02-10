package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.BancoCentral;
import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.domain.Extraccion;
import com.fpmislata.banco.business.domain.MovimientoBancario;
import com.fpmislata.banco.business.domain.RolMovimiento;
import com.fpmislata.banco.business.domain.Transaccion;
import com.fpmislata.banco.business.service.BancoCentralProvider;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.business.service.MovimientoBancarioService;
import com.fpmislata.banco.business.service.TransaccionService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.json.JsonTransformer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpHeaders.USER_AGENT;

public class TransaccionServiceImplLocal implements TransaccionService {

    @Autowired
    MovimientoBancarioService movimientoBancarioService;

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    BancoCentralProvider bancoCentralProvider;

    @Autowired
    JsonTransformer jsonTransformer;

    public BancoCentral getUrlByCCC(BancoCentral bancoCentral) throws BusinessException {
        BancoCentral bancoCentralAnswer = bancoCentralProvider.getBancoCentralAnswer(bancoCentral);

        return bancoCentralAnswer;
    }

    public void retirar(Extraccion extraccion) throws BusinessException, IOException {
        String url = extraccion.getUrl();

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        String extraccionJson = jsonTransformer.objectToJson(extraccion);
        post.setEntity(new StringEntity(extraccionJson));
        try {
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader entity = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = entity.readLine()) != null) {
                result.append(line + entity);
            }

            if (response == HttpStatus.SC_OK) {

                method.getResponseBodyAsString();
            } else {
                br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
                String readLine;
                while (((readLine = br.readLine()) != null)) {
                    System.err.println(readLine);
                }
            }

        } catch (Exception e) {
            throw new BusinessException("Retirar", "Error HTTPClient retirando dinero.");
        } finally {
            client.getConnectionManager().shutdown();
        }

        /*   if (transaccion.getPin() != 2045) {
         throw new BusinessException("Pin", "Código pin Incorrecto.");
         }

         //CCC (completo)
         String numeroCuentaCorrienteOrigen = transaccion.getCuentaOrigen();
         CuentaBancaria cuentaBancaria = cuentaBancariaService.getByNumeroCuentaFull(numeroCuentaCorrienteOrigen);

         if (!cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidad().equals(numeroCuentaCorrienteOrigen.trim().substring(0, 4))) {
         throw new BusinessException("Código de Entidad", "Introduzca correctamente en Cuenta Origen.");
         }
         if (!cuentaBancaria.getSucursalBancaria().getCodigoSucursalBancaria().equals(numeroCuentaCorrienteOrigen.trim().substring(4, 8))) {
         throw new BusinessException("Código Sucursal", "Introduzca correctamente en Cuenta Origen.");
         }
         if (!cuentaBancaria.getDigitoControl().equals(numeroCuentaCorrienteOrigen.trim().substring(8, 10))) {
         throw new BusinessException("Digito de Control", "Introduzca correctamente en Cuenta Origen.");
         }
         //Número de Cuenta sólo
         if (!cuentaBancaria.getNumeroCuenta().equals(numeroCuentaCorrienteOrigen.trim().substring(10, 20))) {
         throw new BusinessException("Número de Cuenta", "Introduzca correctamente en Cuenta Origen.");
         }
        
         MovimientoBancario OrigenMovimientoBancario = new MovimientoBancario(cuentaBancaria, transaccion.getConcepto(), transaccion.getImporte(), new Date());
         OrigenMovimientoBancario.setTipoMovimiento(RolMovimiento.debe);

         BigDecimal importe = transaccion.getImporte();
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
         }*/
    }

    public void movimientoHaber(String concepto, String ccc, BigDecimal importe) throws BusinessException {
        MovimientoBancario DestinoMovimientoBancario = new MovimientoBancario();

        DestinoMovimientoBancario.setConcepto(concepto);
        DestinoMovimientoBancario.setImporte(importe);
        DestinoMovimientoBancario.setTipoMovimiento(RolMovimiento.haber);
        DestinoMovimientoBancario.setFecha(new Date());

        String numeroCuentaDestino = ccc;
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

    @Override
    public void insert(Transaccion transaccion) throws BusinessException {
        if (transaccion.getPin().equals("2045")) {
            throw new BusinessException("Pin", "Código pin Incorrecto.");
        }

        BancoCentral bancoCentralAnswer = this.getUrlByCCC(new BancoCentral(transaccion.getCuentaOrigen(), transaccion.getPin(), transaccion.getCuentaDestino().substring(0, 4)));

        try {
            Extraccion extraccion = new Extraccion(bancoCentralAnswer.getCodigoCuentaCorriente(), transaccion.getConcepto(), bancoCentralAnswer.getPin(), bancoCentralAnswer.getUrl(), transaccion.getImporte());
            this.retirar(extraccion);
        } catch (Exception e) {
            throw new BusinessException("Extraccion", "Error retirando dinero del banco origen.");
        }

        this.movimientoHaber(transaccion.getConcepto(), transaccion.getCuentaDestino(), transaccion.getImporte());

    }

    @Override
    public void movimientoRetirar(Transaccion transaccion) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
