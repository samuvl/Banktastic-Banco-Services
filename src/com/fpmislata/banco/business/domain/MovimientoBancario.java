package com.fpmislata.banco.business.domain;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lliurex
 */
public class MovimientoBancario implements Serializable {
    
    private int idMovimientoBancario;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String tipoMovimiento;
    
    @NotNull
    private int idCuentaBancaria;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String concepto;
    
    @NotNull
    private int cantidad;

    private Date fechaMovimiento;

    public MovimientoBancario (){
        
    }
    
    public MovimientoBancario(String tipoMovimiento, int idCuentaBancaria, String concepto, int cantidad, Date fechaMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.idCuentaBancaria = idCuentaBancaria;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdMovimientoBancario() {
        return idMovimientoBancario;
    }

    public void setIdMovimientoBancario(int idMovimientoBancario) {
        this.idMovimientoBancario = idMovimientoBancario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fechaMovimiento;
    }

    public void setFecha(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    
    
}
