package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Nekrasov", (byte) 111);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(0).getName() + "' добавлен в базу данных");
        userService.saveUser("Anna", "Petrova", (byte) 11);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(1).getName() + "' добавлен в базу данных");
        userService.saveUser("Oleg", "Ivanov", (byte) 1);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(2).getName() + "' добавлен в базу данных");
        userService.saveUser("VV", "SS", (byte) 2);
//        System.out.println("User c имненем '" + userService.getAllUsers().get(3).getName() + "' добавлен в базу данных");

        System.out.println("User c имненем '" + userService.getAllUsers().toString() + "' добавлен в базу данных");
//        List<User> users = userService.getAllUsers(); // class User
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
}

