package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
     private static Connection conn;

    public static Connection getConn() {
        getConnection();
        return conn;
    }

    public static void getConnection() {
        final String hostName = "localhost";
        final String dbName = "kata_db";
        final String userName = "root";
        final String password = "root";
        final String DB_Driver = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL, userName, password);
            conn = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }
    }
}

