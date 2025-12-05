package org.example2.library.util;

public class BookValidator {
    public static boolean isValidTitle(String t){
        return t != null && !t.trim().isEmpty();
    }

    public static boolean isValidAuthor(String t){
        return t != null && t.matches("[A-Za-z ]+");
    }

    public static boolean isValidYear(int y){
        return y >= 1000 & y <= 2025;
    }
}
