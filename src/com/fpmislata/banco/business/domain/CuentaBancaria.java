package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Size;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CuentaBancaria implements Serializable {

    private int idCuentaBancaria;
    
//Cambiar a 16 para presentar:
    @Size(min = 2, max = 16)
    private String numeroCuenta;
    
    @Size(min = 2, max = 2)
    private String digitoControl;
    
    
    private Usuario usuario;
    
    private SucursalBancaria sucursalBancaria;

    @NotNull
    private BigDecimal saldo;

    private Date fechaCreacion;
    
  
    public CuentaBancaria() {
    }

    public CuentaBancaria(String numeroCuenta, Usuario usuario, BigDecimal saldo, Date fechaCreacion) {
        this.numeroCuenta = numeroCuenta;
        this.usuario = usuario;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getnumeroCuenta() {
        return numeroCuenta;
    }

    public void setnumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
