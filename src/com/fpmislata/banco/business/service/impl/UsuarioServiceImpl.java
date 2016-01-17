package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import com.fpmislata.banco.security.PasswordManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samu_
 */
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario> implements UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usuarioDAO.findByNombre(nombre);
    }

    @Override
    public Usuario getByNick(String nick) throws BusinessException {
        return usuarioDAO.getByNick(nick);
    }

}
