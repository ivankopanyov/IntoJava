package model;

import abstractions.ValidateResult;


/**
 * Класс, описывающий валидатор пользователей;
 * */
public class UserValidatorV2 extends UserValidator<IdentityUser> {

    public UserValidatorV2() {
        super("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
                "^.{8,}$",
                null,
                "\nПароль должен содержать минимум 8 символов");
    }

    /**
     * Инициализация объекта валидатора.
     * @param emailPattern Паттерн для проверки емейла.
     * @param passwordPattern Паттерн для проверки пароля.
     */
    public UserValidatorV2(String emailPattern, String passwordPattern) {
        super(emailPattern, passwordPattern);
    }

    /**
     * Метод проверки пользователя на валидность.
     * @param user Объект для проверки.
     * @return Результат проверки.
     */
    @Override
    public ValidateResult isValid(IdentityUser user) {
        return super.isValid(user);
    }
}
