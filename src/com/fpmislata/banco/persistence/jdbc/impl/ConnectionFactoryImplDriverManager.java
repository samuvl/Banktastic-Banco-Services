package com.fpmislata.banco.persistence.jdbc.impl;

import com.fpmislata.banco.persistence.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 *
 * @author alumno
 */
public class ConnectionFactoryImplDriverManager implements ConnectionFactory {


    @Override
    public Connection getConnection() {
        try {
            Connection conection;
            Class.forName("com.mysql.jdbc.Driver");
            conection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "banco", "banco");
            
            return conection;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex){
            throw new RuntimeException("Falllo al registrar el Driver");
        }
    }

    @Override
    public void close(Connection conection) {
        try {
                conection.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

