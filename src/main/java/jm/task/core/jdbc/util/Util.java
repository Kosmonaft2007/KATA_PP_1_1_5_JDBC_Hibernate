package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import javax.imageio.spi.ServiceRegistry;
import java.lang.module.Configuration;
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
    private static SessionFactory sessionFactory; // параметры программы ХАЙБЕРНЕТ


    public static Connection getConnection() throws SQLException { //метод для класики
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            if (!connection.isClosed()){
//                System.out.println("Correct connection to db!!");
//            }
//            connection.close();
//            if (connection.isClosed()){
//                System.out.println("connection closed : (");
//            }

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, DB_USERNAME);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

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
