package model;

import abstractions.User;
import abstractions.ValidateResult;
import abstractions.Validator;

import java.util.regex.Pattern;

/**
 * Класс, описывающий валидатор.
 * @param <TUser> Тип объекта пользователя, проверяемого на валидность.
 */
public class UserValidator<TUser extends User> implements Validator<TUser> {

    /**
     * Паттерн для проверки емейла.
     */
    private final String emailPattern;

    /**
     * Паттерн для проверки пароля.
     */
    private final String passwordPattern;

    /**
     * Описание требований для емейла.
     */
    private String emailDescription;

    /**
     * Описание требование для пароля.
     */
    private String passwordDescription;

    /**
     * Инициализация объекта валидатора.
     * @param emailPattern Паттерн для проверки емейла.
     * @param passwordPattern Паттерн для проверки пароля.
     */
    public UserValidator(String emailPattern, String passwordPattern) {
        this.emailPattern = emailPattern;
        this.passwordPattern = passwordPattern;
    }

    /**
     * Инициализация объекта валидатора.
     * @param emailPattern Паттерн для проверки емейла.
     * @param passwordPattern Паттерн для проверки пароля.
     * @param emailDescription Описание требований для емейла.
     * @param passwordDescription Описание требование для пароля.
     */
    public UserValidator(String emailPattern, String passwordPattern, String emailDescription, String passwordDescription) {
        this(emailPattern, passwordPattern);
        this.emailDescription = emailDescription != null ? emailDescription : "";
        this.passwordDescription = passwordDescription != null ? passwordDescription : "";
    }

    /**
     * Метод проверки пользователя на валидность.
     * @param user Объект для проверки.
     * @return Результат проверки.
     */
    @Override
    public ValidateResult isValid(TUser user) {
        boolean isValid = true;
        String message = "";

        if (emailPattern != null && !Pattern.matches(emailPattern, user.getEmail())) {
            isValid = false;
            message = "Некорректный e-mail! " + emailDescription;
        }

        if (passwordPattern != null && !Pattern.matches(passwordPattern, user.getPassword())) {
            isValid = false;
            message += "\nНекорректный пароль! " + passwordDescription;
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
