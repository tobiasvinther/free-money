package com.example.freemoneynoscam.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailService {

    public static boolean validateEmail(String email) {
        //Regular Expression
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        //return if email matches
        return matcher.matches();

    }

}
