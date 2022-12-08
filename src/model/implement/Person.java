package model.implement;

import model.Model;
import model.enums.Gender;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, описывающий человека.
 */
public class Person implements Model {

    /**
     * Имя.
     */
    private final String firstName;

    /**
     * Фамилия.
     */
    private final String lastName;

    /**
     * Отчество.
     */
    private final String patronymic;

    /**
     * Дата рождения.
     */
    private final Date birthday;

    /**
     * Номер телефона.
     */
    private final long phone;

    /**
     * Пол.
     */
    private final Gender gender;

    /**
     * Формат даты.
     */
    private final SimpleDateFormat dateFormat;

    /**
     * Инициализация объекта человека.
     * @param lastName Фамилия.
     * @param firstName Имя.
     * @param patronymic Отчество.
     * @param birthday Дата рождения.
     * @param phone Номер телефона.
     * @param gender Пол.
     */
    public Person(String lastName, String firstName, String patronymic, Date birthday, long phone, Gender gender) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }


    /**
     * Метод, возвращающий строку с ключом модели.
     * @return Строка с ключом модели.
     */
    @Override
    public String getKey() {
        return lastName;
    }

    /**
     * Переопределение метода преобразования объекта в строку.
     * @return Результат преобразования.
     */
    @Override
    public String toString() {
        return lastName + ' ' + firstName + ' ' + patronymic + ' ' + dateFormat.format(birthday) + ' ' + phone + ' ' +
                gender.toString();
    }
}
