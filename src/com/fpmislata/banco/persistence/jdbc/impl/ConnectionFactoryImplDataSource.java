package com.fpmislata.banco.persistence.jdbc.impl;

import com.fpmislata.banco.persistence.jdbc.ConnectionFactory;
import com.fpmislata.banco.persistence.jdbc.DatasourceFactory;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samuel Lao
 */
public class ConnectionFactoryImplDataSource implements ConnectionFactory {

    @Autowired
    DatasourceFactory datasourceFactory;

    @Override
    public Connection getConnection() {
        try {
            DataSource dataSource = datasourceFactory.getDataSource();
            Connection conexion = dataSource.getConnection();
            return conexion;
        
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
