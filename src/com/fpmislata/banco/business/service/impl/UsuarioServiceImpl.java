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
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Autowired
    PasswordManager passwordManager;

    @Override
    public Usuario get(int id) {
        return usuarioDAO.get(id);
    }

    @Override
    public Usuario insert(Usuario usuario) throws BusinessException {

        return usuarioDAO.insert(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) throws BusinessException {

        return usuarioDAO.update(usuario);
    }

    @Override
    public boolean delete(int id) {
        return usuarioDAO.delete(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usuarioDAO.findByNombre(nombre);
    }

    @Override
    public Usuario getByNick(String nick) throws BusinessException {
        return usuarioDAO.getByNick(nick);
    }

}
