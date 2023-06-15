package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement stat;

    public UserDaoJDBCImpl() {
        try {
            Util.getConnection();
            stat = Util.getConn().createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createUsersTable() {
        String updSQL = "CREATE TABLE IF NOT EXISTS user (" +
                                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "first_name VARCHAR(50) ," +
                                "last_name VARCHAR(50)," +
                                "age TINYINT)";
        try {
            stat.executeUpdate(updSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String updSQL = " DROP TABLE IF EXISTS user";
        try {
            stat.executeUpdate(updSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String updSQL = "INSERT INTO user (first_name, last_name, age) VALUES('" +
                name + "','" + lastName + "'," + age + ")";
        try {
            stat.executeUpdate(updSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.printf("User с именем – %s добавлен в базу данных.\n", name);
    }

    public void removeUserById(long id) {
        String updSQL = "DELETE FROM user WHERE id = " + id;
        try {
            stat.executeUpdate(updSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet results;
        String querySQL = "SELECT * FROM user";
        try {
            results = stat.executeQuery(querySQL);
            while (results.next()) {
                User user = new User();
                user.setId(results.getLong(1));
                user.setName(results.getString(2));
                user.setLastName(results.getString(3));
                user.setAge(results.getByte(4));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String updSQL = "TRUNCATE TABLE user";
        try {
            stat.executeUpdate(updSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
