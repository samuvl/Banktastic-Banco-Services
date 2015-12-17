package com.fpmislata.banco.persistence.jdbc.impl;

import com.fpmislata.banco.persistence.jdbc.DatasourceFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatasourceFactoryImpl implements DatasourceFactory {

    @Override
    public DataSource getDataSource() {
        try {
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/MySQLDS");

            return dataSource;

        } catch (NamingException ex) {
            throw new RuntimeException(ex);

        }

    }
}
