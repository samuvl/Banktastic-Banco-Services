package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SucursalBancaria implements Serializable {//falta añadir las anotaciones para que hibernate lo pille

    private int idSucursalBancaria;

    @NotBlank
    @Size(min = 4, max = 4)
    private String codigoSucursalBancaria;

    @NotBlank
    @Size(min = 3, max = 80)
    private String direccion;

    @NotBlank
    @Size(min = 3, max = 20)
    private String telefono;

    @NotNull
    private EntidadBancaria entidadBancaria;

    @NotNull
    private Date fechaCreacion;

    public SucursalBancaria() {
    }

    public SucursalBancaria(String codigoSucursalBancaria, String direccion, String telefono) {
        this.codigoSucursalBancaria = codigoSucursalBancaria;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdSucursalBancaria() {
        return idSucursalBancaria;
    }

    public void setIdSucursalBancaria(int idSucursalBancaria) {
        this.idSucursalBancaria = idSucursalBancaria;
    }

    public EntidadBancaria getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(EntidadBancaria entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    public String getCodigoSucursalBancaria() {
        return codigoSucursalBancaria;
    }

    public void setCodigoSucursalBancaria(String codigoSucursalBancaria) {
        this.codigoSucursalBancaria = codigoSucursalBancaria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
