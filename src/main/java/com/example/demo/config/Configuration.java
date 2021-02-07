package com.example.demo.config;

import org.springframework.context.annotation.Bean;

import java.sql.*;

public class Configuration {

    String url = "jdbc:mysql://localhost:3306/petshop";
    String username = "root";
    String password = "";

    @Bean
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "?user=" + username
                + "&password=" + password + "");
        return connection;
    }
}
