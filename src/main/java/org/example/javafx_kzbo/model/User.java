package org.example.javafx_kzbo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class User {

    private static final AtomicInteger idGenerator = new AtomicInteger(new Random().nextInt());


    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = idGenerator.getAndIncrement();
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public User(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
}
