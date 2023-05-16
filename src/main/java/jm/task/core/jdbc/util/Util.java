package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_PP_1.4";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12041961";
    private static SessionFactory sessionFactory; // параметры программы ХАЙБЕРНЕТ


    // 4 замечание

    public static Connection getConnection() throws SQLException { //метод для класики
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
