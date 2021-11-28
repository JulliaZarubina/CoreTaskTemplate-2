package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String username = "root";
    private static final String password = "q123";
    private static final String url = "jdbc:mysql://localhost:3306/userstable";
    private static final String driverjdbc = "com.mysql.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            if (!connection.isClosed()) {
                System.out.println("Connection OK");
            }

        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return connection;
    }
}
