package com.example.freemoneynoscam.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCwriter {

    private static String url;
    private static String user;
    private static String password;

    private static Connection connection = null;

    public JDBCwriter() {
        connection = getConnection();
    }

    public static Connection getConnection() {
        if(connection != null) {
            return connection;
        }

        try (InputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Successfully connected to database");
        } catch (SQLException | IOException e) {
            System.out.println("Couldn't connect to database. Error: " + e);
            e.printStackTrace();
        }
        return connection;
    }

    public String printEmails() {
        String resultString = "";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * from emails");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
                resultString = resultString + "<br>" + resultSet.getString(1) + " - " + resultSet.getString(2);
            }
            System.out.println("Successfully fetched emails from database");
        } catch (SQLException error) {
            System.out.println("Could not fetch emails from database");
            error.printStackTrace();
        }
        return resultString;
    }

    public static void insertEmailToDatabase(String email) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO emails VALUES (default,?)"); //default because it auto increments
            preparedStatement.setString(1, email);
            preparedStatement.execute();
        } catch(SQLException exception) {
            System.out.println("Something went wrong when adding email to database");
            exception.printStackTrace();
            }
    }

}
