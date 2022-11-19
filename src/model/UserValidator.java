package model;

import abstractions.User;
import abstractions.ValidateResult;
import abstractions.Validator;

import java.util.regex.Pattern;

public class UserValidator<TUser extends User> implements Validator<TUser> {

    private final String emailPattern;
    private final String passwordPattern;
    private String emailDescription;
    private String passwordDescription;

    public UserValidator(String emailPattern, String passwordPattern) {
        this.emailPattern = emailPattern;
        this.passwordPattern = passwordPattern;
    }

    public UserValidator(String emailPattern, String passwordPattern, String emailDescription, String passwordDescription) {
        this(emailPattern, passwordPattern);
        this.emailDescription = emailDescription != null ? emailDescription : "";
        this.passwordDescription = passwordDescription != null ? passwordDescription : "";
    }

    @Override
    public ValidateResult isValid(TUser user) {
        boolean isValid = true;
        String message = "";

        if (emailPattern != null && Pattern.matches(emailPattern, user.getEmail())) {
            isValid = false;
            message = "Некорректный e-mail!" + emailDescription;
        }

        if (passwordPattern != null && Pattern.matches(passwordPattern, user.getPassword())) {
            isValid = false;
            message += "Некорректный пароль!" + passwordDescription;
        }

        boolean finalIsValid = isValid;
        String finalMessage = message;
        return new ValidateResult() {
            @Override
            public boolean isValid() {
                return finalIsValid;
            }

            @Override
            public String getMessage() {
                return finalMessage;
            }
        };
    }
}
