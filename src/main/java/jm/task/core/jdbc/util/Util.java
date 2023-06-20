package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соеденения с БД
    private static  SessionFactory sessionFactory;
    private  Util() {
    }

    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            sessionFactory = Util.getHibernateConnection();
        }
        return sessionFactory;
    }
    private final static String HOST_NAME = "localhost";
    private final static String DB_NAME = "kata_db";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "root";
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String SQL_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private final static String DB_URL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }
        return connection;
    }

    private static SessionFactory getHibernateConnection() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DB_DRIVER);
        properties.put(Environment.URL, DB_URL);
        properties.put(Environment.DIALECT, SQL_DIALECT);
        properties.put(Environment.USER, USER_NAME);
        properties.put(Environment.PASS, PASSWORD);
        return new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .buildSessionFactory();
    }

}

