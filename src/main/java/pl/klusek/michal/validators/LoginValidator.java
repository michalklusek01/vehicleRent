package pl.klusek.michal.validators;

public class LoginValidator {
    public static boolean validateLogin(String login){
        if (login.length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validatePassword(String password){
        if (password.length() < 3){
            return false;
        } else {
            return true;
        }
    }
}
