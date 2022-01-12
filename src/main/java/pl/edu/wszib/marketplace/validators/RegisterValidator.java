package pl.edu.wszib.marketplace.validators;

import pl.edu.wszib.marketplace.exceptions.OnRegisterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator {
    private static Pattern loginPattern= Pattern.compile("[a-z0-9]{3,16}$");
    private static Pattern passwordPattern= Pattern.compile("[A-Za-z0-9]{3,16}$");
    private static Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validateLogin(String login)
    {
        validateBasics(login, loginPattern);
    }

    public static void validatePassword(String password)
    {
        validateBasics(password, passwordPattern);
    }

    public static void validateEmail(String email)
    {
        validateBasics(email, emailPattern);
    }

    private static void validateBasics(String value, Pattern pattern)
    {
        if(value == null) throw new OnRegisterException();

        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches()) throw new OnRegisterException();
    }
}