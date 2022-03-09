package com.esprit.examen.config;

import java.util.regex.Pattern;

public class RegexTests {

    private RegexTests() {
    }

    private static final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String STRING_PATTERN = "[[A-Z-a-z]+\u0020]+";
    private static final String MAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_PATTERN = "[0-9(-)$]+";


    public static boolean isValidName(String str) {
        return (str.length() != 0 && Pattern.matches(STRING_PATTERN, str));
    }

    public static Boolean isValidMail(String mail) {
        return (mail.length() != 0 && Pattern.matches(MAIL_PATTERN, mail));
    }


    public static boolean isAvalidPhone(String str) {
        return (str.length() != 0 && Pattern.matches(PHONE_PATTERN, str));
    }

    public static boolean isValidPassword(String str) {
        return (str.length() != 0 && Pattern.matches(PATTERN, str));
    }

}