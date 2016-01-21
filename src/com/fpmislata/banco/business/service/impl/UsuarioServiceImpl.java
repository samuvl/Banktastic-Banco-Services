package com.fpmislata.banco.business.service.impl;

import com.aeat.valida.Validador;
import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.BusinessMessage;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import com.fpmislata.banco.security.PasswordManager;
import java.util.ArrayList;
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

    @Override
    public Usuario insert(Usuario usuario) throws BusinessException {

        List<BusinessMessage> businessMessages = new ArrayList<>();

        Validador validador = new Validador();
        int validado = validador.checkNif(usuario.getDni());

        if (validado == 0 || validado < 0) {
            BusinessMessage businessMessage = new BusinessMessage("DNI", "El formato es erróneo.");
            businessMessages.add(businessMessage);
        }

        if (businessMessages.size() > 0) {
            throw new BusinessException(businessMessages);
        }

        return usuarioDAO.insert(usuario);

    }

    @Override
    public Usuario update(Usuario usuario) throws BusinessException {

        List<BusinessMessage> businessMessages = new ArrayList<>();

        Validador validador = new Validador();
        int validado = validador.checkNif(usuario.getDni());

        if (validado == 0 || validado < 0) {
            BusinessMessage businessMessage = new BusinessMessage("DNI", "El formato es erróneo.");
            businessMessages.add(businessMessage);
        }

        if (businessMessages.size() > 0) {
            throw new BusinessException(businessMessages);
        }

        return usuarioDAO.update(usuario);

    }

    @Override
    public Usuario getByDni(String dni) throws BusinessException {
        return usuarioDAO.getByDni(dni);
    }
}
