package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoBancario implements Serializable {

    private int idMovimientoBancario;

    private RolMovimiento tipoMovimiento;
    
    private CuentaBancaria cuentaBancaria;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String concepto;
    
    @NotNull
    private BigDecimal importe;

    @NotNull
    private BigDecimal saldoPosterior;
    
    @NotNull
    private BigDecimal saldoAnterior;
        
    private Date fechaMovimiento;

    public MovimientoBancario (){
        
    }
    
    public MovimientoBancario(CuentaBancaria cuentaBancaria, String concepto, BigDecimal importe, Date fechaMovimiento) {
        this.cuentaBancaria = cuentaBancaria;
        this.concepto = concepto;
        this.importe = importe;
        this.fechaMovimiento = fechaMovimiento;
    }

    public RolMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(RolMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdMovimientoBancario() {
        return idMovimientoBancario;
    }

    public void setIdMovimientoBancario(int idMovimientoBancario) {
        this.idMovimientoBancario = idMovimientoBancario;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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

    public BigDecimal getSaldoPosterior() {
        return saldoPosterior;
    }

    public void setSaldoPosterior(BigDecimal saldoPosterior) {
        this.saldoPosterior = saldoPosterior;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public Date getFecha() {
        return fechaMovimiento;
    }

    public void setFecha(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    
    
}
