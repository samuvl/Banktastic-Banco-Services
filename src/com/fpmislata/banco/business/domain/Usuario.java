package com.fpmislata.banco.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alumno
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable {

    private int idUsuario;

    @NotBlank
    @Size(min = 2, max = 40)
    private String nombre;

    @Email
    @NotBlank
    private String email;

    private String password;
    
    @NotNull
    private Rol rol;

    @Size(min = 2, max = 20)
    @NotBlank
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+$")
    private String nick;

    @NotBlank
    @Size(min = 9, max = 9)
    private String dni;

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
}
