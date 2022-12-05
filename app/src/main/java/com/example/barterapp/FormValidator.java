package com.example.barterapp;

import java.util.regex.Pattern;

public class FormValidator {
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MIN_ITEM_NAME_LENGTH = 3;
    public static final int MAX_ITEM_NAME_LENGTH = 25;
    public static final int MIN_ITEM_DESC_LENGTH = 10;
    public static final int MAX_ITEM_DESC_LENGTH = 50;

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

        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    public static boolean isValidItemName(String name) {
        if (isEmpty(name)) {
            return false;
        }

        return name.length() >= MIN_ITEM_NAME_LENGTH && name.length() <= MAX_ITEM_NAME_LENGTH;
    }

    public static boolean isValidItemDesc(String desc) {
        if (isEmpty(desc)) {
            return false;
        }

        return desc.length() >= MIN_ITEM_DESC_LENGTH && desc.length() <= MAX_ITEM_DESC_LENGTH;
    }
}
