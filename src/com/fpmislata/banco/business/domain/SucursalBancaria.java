/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author Lliurex
 */
@JsonIgnoreProperties({ "entidadBancaria" })

public class SucursalBancaria implements Serializable{//falta añadir las anotaciones para que hibernate lo pille
    private int IdSucursalBancaria;
    private EntidadBancaria entidadBancaria;

    public SucursalBancaria() {
    }

    public SucursalBancaria(int codigoSucursal, EntidadBancaria entidadBancaria) {
        this.IdSucursalBancaria = codigoSucursal;
        this.entidadBancaria = entidadBancaria;
    }

    public int getIdSucursalBancaria() {
        return IdSucursalBancaria;
    }

    public void setIdSucursalBancaria(int IdSucursalBancaria) {
        this.IdSucursalBancaria = IdSucursalBancaria;
    }

    public EntidadBancaria getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(EntidadBancaria entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }
    
    
}
