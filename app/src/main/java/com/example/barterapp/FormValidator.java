package com.example.barterapp;

import java.util.regex.Pattern;

public class FormValidator {
    static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    static final int MAX_PASSWORD_LENGTH = 8;

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }

        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }

        return password.length() > MAX_PASSWORD_LENGTH;
    }
}
