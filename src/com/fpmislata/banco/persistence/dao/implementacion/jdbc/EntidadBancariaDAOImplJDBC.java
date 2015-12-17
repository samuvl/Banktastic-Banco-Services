package com.fpmislata.banco.persistence.dao.implementacion.jdbc;

import com.fpmislata.banco.persistence.jdbc.ConnectionFactory;
import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.persistence.dao.EntidadBancariaDAO;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAOImplJDBC implements EntidadBancariaDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public EntidadBancaria get(int idEntidadBancaria) {
        EntidadBancaria entidadBancaria;
        try {
            Connection conexion = connectionFactory.getConnection();

            String query = "SELECT * FROM entidadbancaria WHERE idEntidadBancaria = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, idEntidadBancaria);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entidadBancaria = new EntidadBancaria(resultSet.getString("nombre"), resultSet.getString("codigoEntidad"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                entidadBancaria.setFechaCreacion(resultSet.getDate("fechaCreacion"));
                entidadBancaria.setIdEntidadBancaria(resultSet.getInt("idEntidadBancaria"));
                entidadBancaria.setDireccion(resultSet.getString("direccion"));
            } else {
                entidadBancaria = null;
            }

            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return entidadBancaria;
    }

    /**
     *
     * @param entidadBancaria
     * @return
     * @throws BusinessException
     */
    @Override
    public EntidadBancaria insert(EntidadBancaria entidadBancaria) throws BusinessException {
        EntidadBancaria entidadBancariaDevolver;
        Connection conexion = connectionFactory.getConnection();

        try {
            int id;

            String query = "INSERT INTO entidadbancaria (nombre, codigoEntidad, fechaCreacion, direccion, cif) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entidadBancaria.getNombre());
            preparedStatement.setString(2, entidadBancaria.getCodigoEntidad());
            preparedStatement.setDate(3, new java.sql.Date(entidadBancaria.getFechaCreacion().getTime()));
            preparedStatement.setString(4, entidadBancaria.getDireccion());
            preparedStatement.setString(5, entidadBancaria.getCif());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                entidadBancariaDevolver = this.get(id);
            } else {
                throw new RuntimeException("No se ha insertado ninguna Entidad");
            }

            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            entidadBancariaDevolver = null;
            if (ex.getErrorCode() == 1062 && ex.getSQLState().equals("23000")) {
                throw new BusinessException("CodigoEntidad: ", "El valor está duplicado");
            } else {
                throw new RuntimeException(ex);
            }
        }
        return entidadBancariaDevolver;
    }

    /**
     *
     * @param entidadBancaria
     * @return
     * @throws BusinessException
     */
    @Override
    public EntidadBancaria update(EntidadBancaria entidadBancaria) throws BusinessException {
        EntidadBancaria entidadBancariaDevolver;
        try {
            Connection conexion = connectionFactory.getConnection();

            String query = "UPDATE entidadbancaria SET nombre = ?, codigoEntidad = ?, fechaCreacion = ?, direccion = ?, cif = ? WHERE idEntidadBancaria = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, entidadBancaria.getNombre());
            preparedStatement.setString(2, entidadBancaria.getCodigoEntidad());
            preparedStatement.setDate(3, new java.sql.Date(entidadBancaria.getFechaCreacion().getTime()));
            preparedStatement.setString(4, entidadBancaria.getDireccion());
            preparedStatement.setString(5, entidadBancaria.getCif());
            preparedStatement.setInt(6, entidadBancaria.getIdEntidadBancaria());
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas == 1) {
                entidadBancariaDevolver = this.get(entidadBancaria.getIdEntidadBancaria());
            } else if (filasActualizadas == 0) {
                throw new RuntimeException("No se ha actualizado ninguna fila: " + filasActualizadas);
            } else {
                throw new RuntimeException("Soy un paranoico; Filas actualizadas: " + filasActualizadas);
            }
            connectionFactory.close(conexion);

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062 && ex.getSQLState().equals("23000")) {
                throw new BusinessException("CodigoEntidad: ", "El valor está duplicado");
            } else {
                throw new RuntimeException(ex);
            }
        }
        return entidadBancariaDevolver;
    }

    @Override
    public boolean delete(int idEntidadBancaria) {
        boolean exito = false;
        try {
            Connection conexion = connectionFactory.getConnection();

            String query = "DELETE FROM entidadbancaria WHERE idEntidadBancaria = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, idEntidadBancaria);
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
    public List<EntidadBancaria> findAll() {
        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

        try {
            Connection conexion = connectionFactory.getConnection();
            String query = "SELECT * FROM entidadbancaria";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EntidadBancaria entidadBancaria = new EntidadBancaria(resultSet.getString("nombre"), resultSet.getString("codigoEntidad"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                entidadBancaria.setDireccion(resultSet.getString("direccion"));
                if (resultSet.getDate("fechaCreacion") == null) {
                    entidadBancaria.setFechaCreacion(new Date());
                } else {
                    entidadBancaria.setFechaCreacion(new java.util.Date(resultSet.getDate("fechaCreacion").getTime()));
                }
                entidadBancaria.setIdEntidadBancaria(resultSet.getInt("idEntidadBancaria"));
                entidadesBancarias.add(entidadBancaria);
            }
            //Thread.sleep(14000);
            connectionFactory.close(conexion);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return entidadesBancarias;

    }

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

        try {
            Connection conexion = connectionFactory.getConnection();
            String query = "SELECT * FROM entidadbancaria where nombre = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EntidadBancaria entidadBancaria = new EntidadBancaria(resultSet.getString("nombre"), resultSet.getString("codigoEntidad"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                entidadBancaria.setDireccion(resultSet.getString("direccion"));
                if (resultSet.getDate("fechaCreacion") == null) {
                    entidadBancaria.setFechaCreacion(new Date());
                } else {
                    entidadBancaria.setFechaCreacion(new java.util.Date(resultSet.getDate("fechaCreacion").getTime()));
                }
                entidadBancaria.setIdEntidadBancaria(resultSet.getInt("idEntidadBancaria"));
                entidadesBancarias.add(entidadBancaria);
            }
            connectionFactory.close(conexion);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return entidadesBancarias;
    }
}
