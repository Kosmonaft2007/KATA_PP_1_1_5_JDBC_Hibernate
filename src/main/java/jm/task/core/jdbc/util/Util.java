package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import org.hibernate.cfg.Configuration;
//import java.lang.module.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
//import javax.imageio.spi.ServiceRegistry;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_PP_1.4";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12041961";
    private static SessionFactory sessionFactory = null;
    public static Connection getConnection = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", DB_URL)
                        .setProperty("hibernate.connection.username", DB_USERNAME)
                        .setProperty("hibernate.connection.password", DB_PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}
