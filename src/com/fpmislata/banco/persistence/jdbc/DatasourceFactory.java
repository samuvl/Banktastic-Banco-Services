
package com.fpmislata.banco.persistence.jdbc;

import javax.sql.DataSource;

public interface DatasourceFactory {
    public DataSource getDataSource();
}
