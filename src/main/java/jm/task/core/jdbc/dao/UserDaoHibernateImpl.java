package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            // нужен ли здесь автоинкремент у айди, если он прописан в аннотации?
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT(3))");
        } catch (SQLException e) {
            System.out.println("Create table exception");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS User");
        } catch (SQLException e) {
            System.out.println("Drop table exception");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        // здесь serializable может быть поле, метод, класс?(2 параметр)
        //session.delete(String.format("FROM User WHERE id = %d", id));
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return Util.getSessionFactory().openSession().createQuery("FROM User").list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE User");
        transaction.commit();
        session.close();
    }
}
