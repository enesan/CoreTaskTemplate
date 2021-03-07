package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT primary key not null auto_increment," +
                    "name VARCHAR(50) not null," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT )");
        } catch (SQLException e) {
            System.out.println("Create table Exception");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Дроптэйбл экзепшн");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO users (name, lastName, age) " +
                    "VALUES(%s, %s, %d)", name, lastName, age));
            System.out.printf("User named %s added successfully\n", name);
        } catch (SQLException e) {
            System.out.println("Добавление юзера экзепшшн");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate(String.format("DELETE FROM users WHERE id = %d", id));
        } catch (SQLException e) {
            System.out.println("Удаление юзера экзепшн");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.connectDB().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM usersdb.users");
            while (rs.next()) {
                // какой ещё есть способ?
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");

                User user = new User('\'' + name + '\'', '\'' + lastName + '\'', age);
                System.out.println(user.toString());
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Юзерсгет экзепшн");
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("Транкейт экзепшн");
        }
    }
}
