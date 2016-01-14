package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CuentaBancaria implements Serializable {

    private int idCuentaBancaria;
    
    @NotNull
    private String nombreTitular;
    
    //@NotNull
    //@Column(length=16)
    private int nCuenta;
    
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="idSucursalBancaria")
    private SucursalBancaria sucursalBancaria;

    @NotNull
    private BigDecimal saldo;

    public CuentaBancaria() {

    }

    public CuentaBancaria(String nombreTitular, int nCuenta, BigDecimal saldo) {
        this.nombreTitular = nombreTitular;
        this.nCuenta = nCuenta;
        this.saldo = saldo;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombre) {
        this.nombreTitular = nombre;
    }

    public int getnCuenta() {
        return nCuenta;
    }

    public void setnCuenta(int nCuenta) {
        this.nCuenta = nCuenta;
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
