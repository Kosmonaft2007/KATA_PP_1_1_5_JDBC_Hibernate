package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import org.hibernate.cfg.Configuration;
//import java.lang.module.Configuration;
import org.hibernate.service.ServiceRegistry;
//import javax.imageio.spi.ServiceRegistry;


import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_PP_1.4";
    //    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false"; ????
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12041961";
    private static SessionFactory sessionFactory = null; // параметры программы ХАЙБЕРНЕТ


//    public static Connection getConnection() throws SQLException { //метод для класики
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
////            if (!connection.isClosed()){
////                System.out.println("Correct connection to db!!");
////            }
////            connection.close();
////            if (connection.isClosed()){
////                System.out.println("connection closed : (");
////            }
//
//            return connection;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    public static SessionFactory getConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", DB_URL)
                        .setProperty("hibernate.connection.username", DB_USERNAME)
                        .setProperty("hibernate.connection.password", DB_PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);

//                configuration.addAnnotatedClass(User.class);

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
