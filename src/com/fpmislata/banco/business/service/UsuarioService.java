package com.fpmislata.banco.business.service;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author samu_
 */
public interface UsuarioService extends GenericService<Usuario> {

    List<Usuario> findByNombre(String nombre);
    Usuario getByNick(String nick) throws BusinessException ;

}