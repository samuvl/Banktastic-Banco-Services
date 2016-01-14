package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoBancario implements Serializable {
    /*
idMovimiento - fechayHora movimiento - concepto - importe - saldo (CALCULADO) - tipoMovimiento (ENUM)
*/
    private int idMovimientoBancario;

    @Column(name = "tipoMovimiento")
    @Enumerated(EnumType.STRING)
    private RolMovimiento tipoMovimiento;
    
    @ManyToOne
    @JoinColumn(name="idCuentaBancaria")
    private CuentaBancaria cuentaBancaria;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String concepto;
    
    @NotNull
    private BigDecimal importe;

    @NotNull
    private BigDecimal saldo;
        
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


    public Date getFecha() {
        return fechaMovimiento;
    }

    public void setFecha(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    
    
}
