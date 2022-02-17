package com.esprit.examen.config;
import java.sql.Date;
import java.util.regex.*;

public class RegexTests {


    public static boolean isValidDate(String date){
        return Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$\n+",date);
    }
    public  static boolean isValidName(String str)
    {
        return Pattern.matches("[[A-Z-a-z]+\\u0020]+",str);
    }

    public static  Boolean isValidMail(String mail)
    {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$",mail);
    }
    public static Boolean isValidPrice(String price)
    {
        return Pattern.matches("[0-9]+([,.][0-9]{1,2})?",price);
    }

    public static boolean isAvalidPhone (String str)
    {
        return (str.length()==0 || Pattern.matches("[0-9(-)$]+",str));
    }
    String passwordPattern = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    public static boolean isValidPassword (String str)
    {
        return (Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$",str));
    }

    public static boolean isValidDescription(String description){
        return Pattern.matches("[a-z0-9_-]{10,150}+",description);
    }

    public static boolean isValidIntitule(String intitule){
        return Pattern.matches("[a-z0-9_-]{10,50}+",intitule);
    }

}