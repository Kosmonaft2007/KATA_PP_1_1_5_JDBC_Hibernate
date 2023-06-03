package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Создание таблицы User(ов)
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        userService.saveUser("Ivan", "Nekrasov", (byte) 111);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(0).getName() + "' добавлен в базу данных");
        userService.saveUser("Anna", "Petrova", (byte) 11);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(1).getName() + "' добавлен в базу данных");
        userService.saveUser("Oleg", "Ivanov", (byte) 1);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(2).getName() + "' добавлен в базу данных");
        userService.saveUser("VV", "SS", (byte) 2);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(3).getName() + "' добавлен в базу данных");


        //Получение всех User из базы и вывод в консоль
        userService.getAllUsers();

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}

