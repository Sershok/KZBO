package org.example.javafx_kzbo.repository;

import org.example.javafx_kzbo.config.ConnectingDB;
import org.example.javafx_kzbo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    ConnectingDB connectingDB = new ConnectingDB();
    Connection connection = connectingDB.connectionsToDB();

    public int signUpUser(User user) {
        String insert = "INSERT INTO users (id, username, password) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

            return user.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String id, String username, String password) {
        String select = "SELECT * FROM users WHERE username = ? AND password = ? AND id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, Integer.parseInt(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when checking the existence of a user in the database", e);
        }
    }

    public User getUser() {
        String selectRandomUser = "SELECT * FROM users ORDER BY RAND() LIMIT 1";

        User user = new User();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectRandomUser)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }

        return user;
    }
}


