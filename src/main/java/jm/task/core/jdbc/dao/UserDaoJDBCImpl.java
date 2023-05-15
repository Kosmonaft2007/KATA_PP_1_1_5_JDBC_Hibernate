package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() { // создаем таблицу пользователя
        try (Statement statement = Util.getConnection().createStatement()) {// СОЗДАЙТЕ ТАБЛИЦУ, ЕСЛИ НЕ СУЩЕСТВУЕТ пользователей
            statement.execute("CREATE TABLE IF NOT EXISTS Users" + "( " +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(32)," +
                    "lastName VARCHAR(32)," +
                    "age TINYINT DEFAULT 0)" +
                    "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() { // удаляем таблицу пользователя
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Users"); // Удаляем ТАБЛИЦУ, ЕСЛИ СУЩЕСТВУЕТ пользователь
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //добавление данных
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO Users (id, name, lastName, age) " + //добавление данных в таблицу
                "values (id, '" + name + "', '" + lastName + "', '" + age + "')")) {
            preparedStatement.executeUpdate();
            System.out.println("User c имненем '" + name + "' добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) { // удалить пользователя по идентификатору
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM Users WHERE id = '" + id + "'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() { // Получить всех пользователей
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM Users")) {
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
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("TRUNCATE TABLE Users;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
