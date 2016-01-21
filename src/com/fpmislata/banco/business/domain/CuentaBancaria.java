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

    @Size(min = 10, max = 10, message = "Debe tener {max} dígitos.")
    private String numeroCuenta;

    @Size(min = 2, max = 2)
    private String digitoControl;

    @NotNull(message = "No puede estar vacío.")
    private Usuario usuario;

    @NotNull(message = "No puede estar vacío.")
    private SucursalBancaria sucursalBancaria;

    private BigDecimal saldo;

    @NotNull(message = "No puede estar vacío.")
    private Date fechaCreacion;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String numeroCuenta, Usuario usuario, Date fechaCreacion) {
        this.numeroCuenta = numeroCuenta;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDigitoControl() {
        return digitoControl;
    }

    public void setDigitoControl(String digitoControl) {
        this.digitoControl = digitoControl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SucursalBancaria getSucursalBancaria() {
        return sucursalBancaria;
    }

    public void setSucursalBancaria(SucursalBancaria sucursalBancaria) {
        this.sucursalBancaria = sucursalBancaria;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
