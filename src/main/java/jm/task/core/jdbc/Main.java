package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

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
        userService.saveUser("Anna", "Petrova", (byte) 11);
        userService.saveUser("Oleg", "Ivanov", (byte) 1);
        userService.saveUser("VV", "SS", (byte) 2);


        //Получение всех User из базы и вывод в консоль
        userService.getAllUsers();

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}

