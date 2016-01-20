package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lliurex
 */
public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario> implements UsuarioDAO {

    @Override
    public List<Usuario> findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT usuario FROM Usuario usuario WHERE nombre=?");
        query.setString(0, nombre);
        List<Usuario> usuarios = query.list();


        return usuarios;
    }

    @Override
    public Usuario getByNick(String nick) throws BusinessException {

        Usuario usuario = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT usuario FROM Usuario usuario WHERE nick=?");
        query.setString(0, nick);

        List<Usuario> usuarios = query.list();

        if (usuarios.isEmpty() || usuarios == null) {
            throw new BusinessException("nick: ", "No existe");
        } else {
            usuario = usuarios.get(0);
        }

        return usuario;
    }

    @Override
    public Usuario getByDni(String dni) throws BusinessException {
        Usuario usuario = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT usuario FROM Usuario usuario WHERE dni=?");
        query.setString(0, dni);

        List<Usuario> usuarios = query.list();
        
        System.out.println(usuarios.size());

        if (usuarios.isEmpty() || usuarios == null) {
            throw new BusinessException("dni: ", "No existe");
        } else {
            usuario = usuarios.get(0);
        }

        return usuario;
    }
}
