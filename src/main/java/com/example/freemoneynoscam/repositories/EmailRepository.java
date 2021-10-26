package com.example.freemoneynoscam.repositories;

import com.example.freemoneynoscam.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmailRepository {

    public static User fetchUser() {
        User testUser = new User(1, "my_email@gmail.com");
        return testUser;
    }

    public static String displayEmails() {
        String resultString = "";
        try {
            PreparedStatement preparedStatement = JDBCwriter.getConnection().prepareStatement("SELECT * from emails");
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

    public static ArrayList<User> displayEmails1() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = JDBCwriter.getConnection().prepareStatement("SELECT * from emails");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User tempUser = new User(resultSet.getInt(1), resultSet.getString(2));
                userList.add(tempUser);
            }
            System.out.println("Successfully fetched emails from database");
        } catch (SQLException error) {
            System.out.println("Could not fetch emails from database");
            error.printStackTrace();
        }
        return userList;
    }

}
