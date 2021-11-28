package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(33), lastname VARCHAR(33), age TINYINT)");
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "users", null);
            if (tables.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE users");
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Таблица не существует");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица уничтожена");

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "users", null);
            if (tables.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Таблица не существует");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "users", null);
            if (tables.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("DElETE FROM users WHERE id = ?");
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Таблица не существует");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User уничтожен");

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "users", null);
            if (tables.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastname"));
                    user.setAge(rs.getByte("age"));

                    users.add(user);
                }
            } else {
                System.out.println("Таблица не существует");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "users", null);
            if (tables.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Таблица не существует");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица очищена");

    }
}
