package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn;
    public UserDaoJDBCImpl() {
        conn = Util.getConn();
    }

    public void createUsersTable() {
        String updSQL = "CREATE TABLE IF NOT EXISTS user (" +
                                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "first_name VARCHAR(50) ," +
                                "last_name VARCHAR(50)," +
                                "age TINYINT)";
        try {
            conn.prepareStatement(updSQL).executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String updSQL = "DROP TABLE IF EXISTS user";
        try {
            conn.prepareStatement(updSQL).executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String updSQL = "INSERT INTO user (first_name, last_name, age) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.printf("User с именем – %s добавлен в базу данных.\n", name);
    }

    public void removeUserById(long id) {
        String updSQL = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet results;
        String querySQL = "SELECT * FROM user";
        try {
            results = conn.prepareStatement(querySQL).executeQuery();
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
            conn.prepareStatement(updSQL).executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
