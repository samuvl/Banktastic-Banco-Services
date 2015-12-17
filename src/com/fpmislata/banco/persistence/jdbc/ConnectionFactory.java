package com.fpmislata.banco.persistence.jdbc;

import java.sql.Connection;

public interface ConnectionFactory {

    public Connection getConnection();

    public void close(Connection conexion);
}
