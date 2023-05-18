package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static Connection con;

    static {
        try {
            con = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {
    }
    @Override   
    public void createUsersTable() { // создаем таблицу пользователя
        try (PreparedStatement preparedStatement = con.prepareStatement("CREATE TABLE IF NOT EXISTS People" + "( " +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(32)," +
                "lastName VARCHAR(32)," +
                "age TINYINT DEFAULT 0)" +
                "");) {// СОЗДАЙТЕ ТАБЛИЦУ, ЕСЛИ НЕ СУЩЕСТВУЕТ пользователей
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() { // удаляем таблицу пользователя
        try (PreparedStatement preparedStatement = con.prepareStatement("DROP TABLE IF EXISTS People")) {
            preparedStatement.executeUpdate(); // Удаляем ТАБЛИЦУ, ЕСЛИ СУЩЕСТВУЕТ пользователь
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //добавление данных
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO People (id, name, lastName, age) " + //добавление данных в таблицу
                "values (id, '" + name + "', '" + lastName + "', '" + age + "')")) {
            preparedStatement.executeUpdate();
            System.out.println("User c имненем '" + name + "' добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) { // удалить пользователя по идентификатору
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM People WHERE id = '" + id + "'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() { // Получить всех пользователей
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM People")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    @Override
    public void cleanUsersTable() { // Чистый пользователь, стабильный
        try (PreparedStatement preparedStatement = con.prepareStatement("TRUNCATE TABLE People;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}















//    public void saveUser(String name, String lastName, byte age) throws SQLException {
//        String dropTable = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
//
//        try (PreparedStatement statement = connection.prepareStatement(dropTable)) {
//            // Определяем значения переменных
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setInt(3, age);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//        }
//    }
