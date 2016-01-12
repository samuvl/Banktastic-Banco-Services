package com.fpmislata.banco.business.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Size;

/**
 *
 * @author Lliurex
 */
public class CuentaBancaria implements Serializable {

    private int idCuentaBancaria;
    @NotNull

    private String nombreTitular;
    @NotNull
    private int nCuenta;

    @Size(min = 2, max = 50)
    private String tipoCuenta;

    @NotNull
    private int idSucursalBancaria;

    @NotNull
    private BigDecimal saldoCuenta;

    public CuentaBancaria() {

    }

    public CuentaBancaria(String nombreTitular, int nCuenta, String tipoCuenta, BigDecimal saldoCuenta) {
        this.nombreTitular = nombreTitular;
        this.nCuenta = nCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoCuenta = saldoCuenta;
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

    public int getIdCuenta() {
        return idCuentaBancaria;
    }

    public void setIdCuenta(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdSucursalBancaria() {
        return idSucursalBancaria;
    }

    public void setIdSucursalBancaria(int idSucursalBancaria) {
        this.idSucursalBancaria = idSucursalBancaria;
    }

    public BigDecimal getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(BigDecimal saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

}
