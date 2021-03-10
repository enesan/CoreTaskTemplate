package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();

        us.dropUsersTable();
        us.createUsersTable();
      //  us.saveUser("'John'", "'Wisdom'", (byte) 35);
      //  us.getAllUsers().get(0);

      //  us.cleanUsersTable();
      //  us.dropUsersTable();
      //  us.createUsersTable();
      //  us.saveUser("'John'", "'Wisdom'", (byte) 35);
      //  us.saveUser("'Alice'", "'Brown'", (byte) 27);
      //  us.saveUser("'Robert'", "'Edison'", (byte) 25);
      //  us.saveUser("'Andy'", "'Hampsteen'", (byte) 32);
//
        us.removeUserById(3);
//
        List<User> usersList = us.getAllUsers();
//
        for (User userr : usersList) {
            System.out.println(userr.toString());
        }


    }
}
