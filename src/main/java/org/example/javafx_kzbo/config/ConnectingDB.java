package org.example.javafx_kzbo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectingDB {

    public Connection connectionsToDB() {
        Connection connection = null;
        try {
            Properties properties = loadPropertiesFile();
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | SQLException e) {
            System.err.println("Error reading configuration file or inserting goods into the database" + e.getMessage());
        }
        return connection;
    }

    private Properties loadPropertiesFile() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        }
        return properties;
    }

}
