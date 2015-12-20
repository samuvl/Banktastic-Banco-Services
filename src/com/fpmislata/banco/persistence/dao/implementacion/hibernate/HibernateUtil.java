package com.fpmislata.banco.persistence.dao.implementacion.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static void buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
    }

    
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    
    public static void closeSessionFactory() {
        sessionFactory.close();
    }

}