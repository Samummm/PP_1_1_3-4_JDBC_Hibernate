package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String hostName = "localhost";
    private final static String dbName = "kata_db";
    private final static String userName = "root";
    private final static String password = "root";
    private final static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final static String dialect = "org.hibernate.dialect.MySQLDialect";
    private final static String dbURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
    private static Connection conn;
    private static SessionFactory factory;

    public static Connection getConn() {
        getConnection();
        return conn;
    }
    public static SessionFactory getFactory() {
        getHibernateConnection();
        return factory;
    }

    private static void getConnection() {
        try {
            Class.forName(dbDriver);
            Connection connection = DriverManager.getConnection(dbURL, userName, password);
            conn = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }
    }

    private static void getHibernateConnection() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, dbDriver);
        properties.put(Environment.URL, dbURL);
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.USER, userName);
        properties.put(Environment.PASS, password);

        factory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .buildSessionFactory();

    }

}

