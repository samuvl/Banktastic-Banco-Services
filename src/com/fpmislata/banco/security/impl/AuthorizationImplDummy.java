package com.fpmislata.banco.security.impl;

import com.fpmislata.banco.business.domain.Rol;
import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.security.Authorization;

/**
 *
 * @author Samuel Lao
 */
public class AuthorizationImplDummy implements Authorization {

    @Override
    public boolean isAuthorizedURL(Usuario usuario, String url, String metodo) {

        boolean isAuthorized;

        
        if (url.equalsIgnoreCase("/banktastic-banco-api/api/session") || url.equalsIgnoreCase("/banktastic-banco-api/api/transaccion") || url.equalsIgnoreCase("/banktastic-banco-api/api/retirar")) {
            isAuthorized = true;
        } else {
            isAuthorized = false;
            if (usuario != null) {
                if (usuario.getRol() == Rol.trabajador) {
                    isAuthorized = true;
                } else if (usuario.getRol() == Rol.cliente) {
                    isAuthorized = false;
                } else {
                    isAuthorized = false;
                }
            }

        }
        return isAuthorized;
    }

}
