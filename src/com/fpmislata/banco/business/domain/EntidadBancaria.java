package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alumno
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntidadBancaria implements Serializable {

    public int idEntidadBancaria;
    
    @NotBlank
    @Size(min = 2, max = 40)
    public String nombre;

    @NotBlank
    @Size(min = 4, max = 4)
    public String codigoEntidad;

    @NotNull
    public Date fechaCreacion;

    @Size(min = 2, max = 50)
    @NotBlank
    public String direccion;

    @NotBlank
    public String cif;

    public EntidadBancaria() {
    }

    public EntidadBancaria(String nombre, String codigoEntidad) {
        this.nombre = nombre;
        this.codigoEntidad = codigoEntidad;
    }

    public int getIdEntidadBancaria() {
        return idEntidadBancaria;
    }

    public void setIdEntidadBancaria(int idEntidadBancaria) {
        this.idEntidadBancaria = idEntidadBancaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

}
