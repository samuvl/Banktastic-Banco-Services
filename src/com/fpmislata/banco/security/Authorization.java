package com.fpmislata.banco.security;

import com.fpmislata.banco.business.domain.Usuario;

/**
 *
 * @author Samuel Lao
 */
public interface Authorization {
    public boolean isAuthorizedURL(Usuario user, String url, String metodo);  
}
