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
import com.fpmislata.banco.business.service.RetirarService;
import com.fpmislata.banco.business.service.TransaccionService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.json.JsonTransformer;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

public class TransaccionServiceImplLocal implements TransaccionService {

    @Autowired
    MovimientoBancarioService movimientoBancarioService;

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    BancoCentralProvider bancoCentralProvider;

    @Autowired
    RetirarService retirarService;

    @Autowired
    JsonTransformer jsonTransformer;

    public String getUrlByCCC(BancoCentral bancoCentral) throws BusinessException {
        BancoCentral bancoCentralAnswer = bancoCentralProvider.getBancoCentralAnswer(bancoCentral);

        String bancoCentralJsonTransformer = jsonTransformer.objectToJson(bancoCentralAnswer);

        return bancoCentralJsonTransformer;
    }

    public void retirar(Extraccion extraccion, String url) throws BusinessException, IOException {
        String requestBody = jsonTransformer.objectToJson(extraccion);
        try {
            URL requestedUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requestedUrl.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("charset", "utf-8");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(requestBody.getBytes("UTF-8"));
            outputStream.close();
            int status = httpURLConnection.getResponseCode();

            System.out.println(status);

            if (status == 500 || status == 400) {
                throw new BusinessException("Retirar", "Error 400 o 500 retirando dinero.");
            }
        } catch (Exception e) {
            throw new BusinessException("Retirar", "Error HTTPClient retirando dinero.");
        }

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
        System.out.println("TERMINA MOVIMIENTO HABER");
    }

    @Override
    public void insert(Transaccion transaccion) throws BusinessException {

        if (!transaccion.getPin().equals("2045")) {
            throw new BusinessException("Pin", " Insert - Código pin Incorrecto.");
        }
        BancoCentral bancoCentralEnviar = new BancoCentral(transaccion.getCuentaOrigen(), transaccion.getPin(), transaccion.getCuentaDestino().substring(0, 4));

        String bancoCentralAnswerJson = this.getUrlByCCC(bancoCentralEnviar);
        BancoCentral bancoCentralAnswer = jsonTransformer.jsonToObject(bancoCentralAnswerJson, BancoCentral.class);
        Extraccion extraccion;

        try {
            extraccion = new Extraccion(bancoCentralAnswer.getCodigoCuentaCorriente(), transaccion.getImporte(), bancoCentralAnswer.getPin(), transaccion.getConcepto());
            this.retirar(extraccion, bancoCentralAnswer.getUrl());
        } catch (Exception e) {
            throw new BusinessException("Extraccion", "Error retirando dinero del banco origen.");
        }

        this.movimientoHaber(transaccion.getConcepto(), transaccion.getCuentaDestino(), transaccion.getImporte());

        retirarService.retirar(extraccion);
    }

    @Override
    public void movimientoRetirar(Transaccion transaccion) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
