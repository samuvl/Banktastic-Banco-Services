package com.fpmislata.banco.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Extraccion implements Serializable{

    private String codigoCuentaCorriente;
    private String concepto;
    private String pin;
    private String url;
    private BigDecimal importe;

    public Extraccion(String codigoCuentaCorriente, String concepto, String pin, String url, BigDecimal importe) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
        this.concepto = concepto;
        this.pin = pin;
        this.url = url;
        this.importe = importe;
    }

    public String getCodigoCuentaCorriente() {
        return codigoCuentaCorriente;
    }

    public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
