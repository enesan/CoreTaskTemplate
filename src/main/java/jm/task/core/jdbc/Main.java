package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();

        int count = 4;
        us.saveUser("John", "Wisdom", (byte)35);
        us.saveUser("Alice", "Brown", (byte)27);
        us.saveUser("Robert", "Edison", (byte)25);
        us.saveUser("Andy", "Hampsteen", (byte)32);

        List<User> usersList = us.getAllUsers();

        for (User user : usersList) {
            System.out.println(user.toString());
        }

        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
