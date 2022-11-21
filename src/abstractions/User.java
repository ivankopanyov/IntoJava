package abstractions;

/**
 * Пользователь.
 */
public interface User {

    /**
     * Метод, возвращающий идентификатор пользователя.
     * @return Идентификатор пользователя.
     */
    String getId();

    /**
     * Метод изменения идентификатора пользователя.
     * @param id Значение идентификатора.
     */
    void setId(String id);

    /**
     * Метод, возвращающий емайл пользователя.
     * @return Емайл пользователя.
     */
    String getEmail();


    /**
     * Метод изменения емейла пользователя.
     * @param email Значение емейла.
     */
    void setEmail(String email);


    /**
     * Метод, возвращающий пароль пользователя.
     * @return Пароль пользователя.
     */
    String getPassword();


    /**
     * Метод изменения пароля пользователя.
     * @param password Значение пароля.
     */
    void setPassword(String password);

    /**
     * Метод, возвращающий копию объекта пользователя.
     * @return Копия объекта пользователя.
     * @param <TUser> Тип пользователя.
     */
    <TUser extends User> TUser copy();
}
