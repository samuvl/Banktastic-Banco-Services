package com.fpmislata.banco.business.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alumno
 */

public class Usuario implements Serializable {

    private int idUsuario;
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String nombre;
    
    @Email
    private String email;
    
    private String password;
    
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    
    @Size(min = 2, max = 20)
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+$")
    private String nick;

    public Usuario() {
    }

       
    public Usuario(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

}
