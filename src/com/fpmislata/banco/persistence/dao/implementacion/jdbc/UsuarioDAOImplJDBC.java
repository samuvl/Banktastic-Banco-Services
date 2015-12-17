package com.fpmislata.banco.persistence.dao.implementacion.jdbc;

/*package com.fpmislata.banco.persistence.dao.impl.jdbc;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.persistence.dao.UsuarioDAO;
import com.fpmislata.banco.persistence.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samu_
 *//*
public class UsuarioDAOImplJDBC implements UsuarioDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public Usuario get(int id) {
        Connection conexion = connectionFactory.getConnection();
        Usuario usuario;
        try {
            String sql = "select * from usuario where idUsuario=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario(resultSet.getString("nombre"), resultSet.getString("password"));
                usuario.setId(resultSet.getInt("idUsuario"));
                usuario.setNick(resultSet.getString("nick"));
                //String rol = resultSet.getString("rol");
                //usuario.setRol(rol);
            } else {
                usuario = null;
            }
            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuario;
    }

    @Override
    public Usuario insert(Usuario usuario) {
        Connection conexion = connectionFactory.getConnection();
        Usuario usuarioDevolver = null;

        try {
            int idUsuario = 0;
            //String sql = "INSERT INTO usuario (nombre, password, rol, nick) VALUES (?, ?, ?, ?)";
            String sql = "INSERT INTO usuario (nombre, password,  nick) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getpassword());
            //preparedStatement.setString(3, usuario.getRol());
            preparedStatement.setString(3, usuario.getNick());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idUsuario = resultSet.getInt(1);
                usuarioDevolver = this.get(idUsuario);
            }

            connectionFactory.close(conexion);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuarioDevolver;
    }

    @Override
    public Usuario update(Usuario usuario) {
        Connection conexion = connectionFactory.getConnection();
        Usuario usuarioDevolver = null;

        try {
            //String sql = "UPDATE usuario SET (nombre= ?, password= ?, rol=?, nick=?";
            String sql = "UPDATE usuario SET (nombre= ?, password= ?, nick=?";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getpassword());
            //preparedStatement.setString(3, usuario.getRol());
            preparedStatement.setString(3, usuario.getNick());

            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas == 1) {
                usuarioDevolver = this.get(usuario.getId());
            } else if (filasActualizadas == 0) {
                throw new RuntimeException("No se ha actualizado ninguna fila: " + filasActualizadas);
            } else {
                throw new RuntimeException("Soy un paranoico" + filasActualizadas);
            }

            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuarioDevolver;
    }

    @Override
    public boolean delete(int id) {
        boolean exito = false;
        Connection conexion = connectionFactory.getConnection();
        try {
            String sql = "DELETE FROM usuario WHERE idUsuario=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            int filasBorradas = preparedStatement.executeUpdate();

            if (filasBorradas == 0) {
                exito = false;
            } else if (filasBorradas == 1) {
                exito = true;
            } else if (filasBorradas > 1) {
                throw new RuntimeException("Demasiadas filas borrasdas: " + filasBorradas);
            } else {
                throw new RuntimeException("Soy un paranoico" + filasBorradas);
            }
            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return exito;
    }

    @Override
    public List<Usuario> findAll() {
        Connection conexion = connectionFactory.getConnection();
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet.getString("nombre"), resultSet.getString("password"));
                usuario.setId(resultSet.getInt("idUsuario"));
                usuario.setNick(resultSet.getString("nick"));
                //usuario.setRol(resultSet.getString("rol"));
                usuarios.add(usuario);
            }
            
            connectionFactory.close(conexion);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuarios;
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conexion = connectionFactory.getConnection();

        try {
            String query = "SELECT * FROM EntidadBancaria where nombre = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet.getString("nombre"), resultSet.getString("password"));
                usuario.setId(resultSet.getInt("idUsuario"));
                usuario.setNick(resultSet.getString("nick"));
                //usuario.setRol(resultSet.getString("rol"));
                usuarios.add(usuario);
            }
            connectionFactory.close(conexion);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());

        }
        return usuarios;
    }
}
*/