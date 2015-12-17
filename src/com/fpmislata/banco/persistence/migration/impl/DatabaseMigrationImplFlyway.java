package com.fpmislata.banco.persistence.migration.impl;

import com.fpmislata.banco.persistence.jdbc.DatasourceFactory;
import com.fpmislata.banco.persistence.migration.DatabaseMigration;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseMigrationImplFlyway implements DatabaseMigration {

    @Autowired 
    DatasourceFactory dataSourceFactory;
    
    @Override
    public void migrate() {

        Flyway flyway = new Flyway();
        
        DataSource dataSource = dataSourceFactory.getDataSource();
        flyway.setDataSource(dataSource);
        flyway.setLocations("com.fpmislata.banco.persistence.migration.scripts");
        flyway.setEncoding("utf-8");
        
        flyway.migrate();
    }

}
