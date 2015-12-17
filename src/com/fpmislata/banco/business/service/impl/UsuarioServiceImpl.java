package com.fpmislata.banco.business.service.impl;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.BusinessMessage;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import com.fpmislata.banco.security.CheckManager;
import com.fpmislata.banco.security.PasswordManager;
import java.util.ArrayList;
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
    
    @Autowired
    CheckManager checkManager;
            
    @Override
    public Usuario get(int id) {
        return usuarioDAO.get(id);
    }

    @Override
    public Usuario insert(Usuario usuario) throws BusinessException {

        List<BusinessMessage> businessMessages = new ArrayList<>();

        if (!checkManager.checkNick(usuario.getNick())) {
            BusinessMessage businessMessage = new BusinessMessage("Nick ", "No puede contener espacios en blanco.");
            businessMessages.add(businessMessage);
        }
        
        if (!passwordManager.checkComplexity(usuario.getPassword())) {
            BusinessMessage businessMessage = new BusinessMessage("Contraseña ", "Debe contener minúsculas, mayúsculas, dígitos y de 6 a 20 caractéres.");
            businessMessages.add(businessMessage);
        }
        
        if (!checkManager.checkEmail(usuario.getEmail())) {
            BusinessMessage businessMessage = new BusinessMessage("Email ", "Debe seguir una parametrización estándar AAA@BBB.CCC");
            businessMessages.add(businessMessage);
        }
        
        if (usuario.getEmail()== null || usuario.getEmail().trim().isEmpty()) {
            BusinessMessage businessMessage = new BusinessMessage("Email ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if ((usuario.getNombre() == null) || (usuario.getNombre().trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Nombre ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if (usuario.getRol() == null || usuario.getRol().toString().trim().isEmpty()) {
            BusinessMessage businessMessage = new BusinessMessage("Rol ", "El campo está vacio.");
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

      
        if (!checkManager.checkNick(usuario.getNick())) {
            BusinessMessage businessMessage = new BusinessMessage("Nick ", "No puede contener espacios en blanco.");
            businessMessages.add(businessMessage);
        }
        
        if ((usuario.getNombre() == null) || (usuario.getNombre().trim().isEmpty())) {
            BusinessMessage businessMessage = new BusinessMessage("Nombre ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }

        if (!checkManager.checkEmail(usuario.getEmail())) {
            BusinessMessage businessMessage = new BusinessMessage("Email ", "Debe seguir una parametrización estándar AAA@BBB.CCC");
            businessMessages.add(businessMessage);
        }
        
        if (usuario.getEmail()== null || usuario.getEmail().trim().isEmpty()) {
            BusinessMessage businessMessage = new BusinessMessage("Email ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if (usuario.getRol() == null || usuario.getRol().toString().trim().isEmpty()) {
            BusinessMessage businessMessage = new BusinessMessage("Rol ", "El campo está vacio.");
            businessMessages.add(businessMessage);
        }
        
        if (businessMessages.size() > 0) {
            throw new BusinessException(businessMessages);
        }

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
