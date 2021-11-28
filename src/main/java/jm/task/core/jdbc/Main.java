package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Stephen", "King", (byte) 54);
        userDaoJDBC.saveUser("Friedrich", "Nietzsche", (byte) 27);
        userDaoJDBC.saveUser("Fyodor", "Dostoevcky", (byte) 45);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.removeUserById(2);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
