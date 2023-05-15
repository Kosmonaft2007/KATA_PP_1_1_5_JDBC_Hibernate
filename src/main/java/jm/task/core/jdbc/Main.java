package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Nekrasov", (byte) 111);
        userService.saveUser("Anna", "Petrova", (byte) 11);
        userService.saveUser("Oleg", "Ivanov", (byte) 1);
        userService.saveUser("VV", "SS", (byte) 2);
        List<User> users = userService.getAllUsers(); // class User
        for (User user : users) {
            System.out.println(user);
        }
    }
}

