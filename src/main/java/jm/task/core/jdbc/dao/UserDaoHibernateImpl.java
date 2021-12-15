package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)").addEntity(User.class);

        query.executeUpdate();
        System.out.println("Таблица создана");
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() throws SQLException {
        Transaction transaction = null;
        Session session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
        query.executeUpdate();
        System.out.println("Таблица уничтожена");
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Transaction transaction = null;
        Session session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        transaction.commit();
        session.close();
        System.out.println("User с именем " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) throws SQLException {
        Transaction transaction = null;
        Session session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        System.out.println("User уничтожен");

    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        User user = new User();

        Session session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User");
        users = query.list();
        transaction.commit();

        return users;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        Transaction transaction = null;

        Session session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User");
        query.executeUpdate();
        System.out.println("Таблица очищена");
        transaction.commit();
        session.close();
    }
}
