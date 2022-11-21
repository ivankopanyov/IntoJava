package model;

import abstractions.User;

import java.util.Objects;

/**
 * Класс, описывающий пользователя.
 */
public class IdentityUser implements User {

    /**
     * Идентификатор пользователя.
     */
    private String id;

    /**
     * Емейл пользовател.
     */
    private String email;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Иницциализация объекта пользователя.
     * @param email Емейл пользователя.
     * @param password Пароль пользователя.
     */
    public IdentityUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Иницциализация объекта пользователя.
     * @param id Идентификатор пользователя.
     * @param email Емейл пользователя.
     * @param password Пароль пользователя.
     */
    public IdentityUser(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Метод, возвращающий идентификатор пользователя.
     * @return Идентификатор пользователя.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Метод изменения идентификатора пользователя.
     * @param id Значение идентификатора.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Метод, возвращающий емайл пользователя.
     * @return Емайл пользователя.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Метод изменения емейла пользователя.
     * @param email Значение емейла.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Метод, возвращающий пароль пользователя.
     * @return Пароль пользователя.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Метод изменения пароля пользователя.
     * @param password Значение пароля.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Метод, возвращающий имя пользователя.
     * @return Имя пользователя.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод изменения имени пользователя.
     * @param value Значение имени.
     */
    public void setFirstName(String value) {
        firstName = value == null ? "" : value;
    }

    /**
     * Метод, возвращающий фамилию пользователя.
     * @return фамилия пользователя.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Метод изменения фамилии пользователя.
     * @param value Значение фамилии.
     */
    public void setLastName(String value) {
        lastName = value == null ? "" : value;
    }

    /**
     * Метод, возвращающий копию объекта пользователя.
     * @return Копия объекта пользователя.
     */
    @Override
    public IdentityUser copy() {
        IdentityUser user = new IdentityUser(id, email, null);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    /**
     * Переопределение метода сравнения объектов пользователей.
     * @param o Объект для сравнения.
     * @return Результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IdentityUser user = (IdentityUser) o;

        return id.equals(user.getId()) || email.equals(user.getEmail());
    }

    /**
     * Переопределение метода, возвращающего хеш-код пользователя.
     * @return Хеш-код пользователя.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    /**
     * Переопределение метода, преобразующего объект пользователя в строку.
     * @return Строка с именем, фамилией и емейлом пользователя.
     */
    @Override
    public String toString() {
        return firstName + ' ' + lastName + " (" + email + ')';
    }
}
