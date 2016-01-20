package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.core.BusinessException;
import java.util.List;

/**
 *
 * @author samu_
 */
public interface UsuarioDAO extends GenericDAO<Usuario> {

    List<Usuario> findByNombre(String nombre);
    Usuario getByNick(String nick) throws BusinessException ;
    Usuario getByDni(String dni) throws BusinessException ;
}
