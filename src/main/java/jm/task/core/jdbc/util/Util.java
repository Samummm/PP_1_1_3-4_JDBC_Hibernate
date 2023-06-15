package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
     private static Connection conn;

    public static Connection getConn() {
        return conn;
    }

    public static void getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "kata_db";
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        conn = connection;
    }
}

