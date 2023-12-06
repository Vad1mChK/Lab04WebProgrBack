package org.vad1mchk.webprogr.lab04.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final int USERNAME_MIN_LENGTH = 4;
    private static final int USERNAME_MAX_LENGTH = 32;
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 32;
    private static final int PASSWORD_MIN_LETTERS = 6;
    private static final int PASSWORD_MIN_DIGITS = 1;
    private static final int PASSWORD_MIN_SPECIALS = 1;
    private final Pattern lettersPattern = Pattern.compile("[A-Za-z]");
    private final Pattern digitsPattern = Pattern.compile("[0-9]");
    private final Pattern specialsPattern = Pattern.compile("[-.,?!]");

    public String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Имя пользователя не должно быть пустым.";
        }

        if (username.length() < USERNAME_MIN_LENGTH || username.length() > USERNAME_MAX_LENGTH) {
            return "Длина имени пользователя должна быть от " +
                    USERNAME_MIN_LENGTH + " до " + USERNAME_MAX_LENGTH + ".";
        }

        return null;
    }

    public String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Имя пользователя не должно быть пустым.";
        }

        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            return "Длина пароля должна быть от " + PASSWORD_MIN_LENGTH + " до " + PASSWORD_MAX_LENGTH + ".";
        }

        if (
            countMatches(password, lettersPattern) < PASSWORD_MIN_LETTERS ||
                countMatches(password, digitsPattern) < PASSWORD_MIN_DIGITS ||
                countMatches(password, specialsPattern) < PASSWORD_MIN_SPECIALS
        ) {
            return "Пароль должен содержать: букв латиницы — не менее " + PASSWORD_MIN_LETTERS + ", " +
                    "цифр — не менее " + PASSWORD_MIN_DIGITS + ", " +
                    "спец. символов ( -.,?! ) — не менее " + PASSWORD_MIN_SPECIALS + ". ";
        }

        return null;
    }

    private static int countMatches(String text, Pattern regex) {
        Matcher matcher = regex.matcher(text);
        int count = 0;
        while (matcher.find()) {
            ++count;
        }
        return count;
    }
}
