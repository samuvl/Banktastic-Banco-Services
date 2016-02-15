

package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;

//faltan las propiedades de hibernate

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaccion implements Serializable{
    
    private int idTransaccion;
    private String cuentaOrigen;
    private String cuentaDestino;
    private String concepto;
    private BigDecimal importe;
    private String pin;

    public Transaccion() {
    }

    public Transaccion(String cuentaOrigen, String cuentaDestino, String concepto, BigDecimal importe, String pin) {
        
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.concepto = concepto;
        this.importe = importe;
        this.pin = pin;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    
    
    

}
