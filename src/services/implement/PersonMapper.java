package services.implement;

import model.Model;
import model.enums.Gender;
import model.implement.Person;
import services.Mapper;
import services.exceptions.MapperException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс, описывающий маппер, преобразующий строку в экземпляр модели.
 */
public class PersonMapper implements Mapper {

    /**
     * Паттерн для проверки символов, содержащихся в имени.
     */
    private static final String NAME_PATTERN = "^[a-zA-Zа-яА-ЯёЁ-]+$";

    /**
     * Формат даты.
     */
    private final SimpleDateFormat formatter;

    /**
     * Инициализация объекта маппера.
     */
    public PersonMapper() {
        formatter = new SimpleDateFormat("dd.MM.yyyy");
    }

    /**
     * Метод преобразования строки в экземпляр модели.
     * @param source Строка для преобразования.
     * @return Результат преобразования.
     * @throws MapperException Невозможно преобразовать строку в экземпляр модели.
     */
    @Override
    public Model map(String source) throws MapperException {
        if (source == null || source.trim().equals(""))
            throw new MapperException("Строка не должна быть пустой!");

        String[] array = source.trim().split("\\s+");

        if (array.length != 6)
            throw new MapperException("Неверное колличество аргументов!");

        List<String> list = new ArrayList<>(List.of(array));

        Date birthday = null;

        for (String str: list) {
            try {
                birthday = formatter.parse(str);
                list.remove(str);
                break;
            } catch (ParseException ignored) { }
        }

        if (birthday == null)
            throw new MapperException("Не указана дата рождения!");

        Long phone = null;

        for (String str: list) {
            try {
                phone = Long.parseLong(str);
                list.remove(str);
                break;
            } catch (NumberFormatException ignored) { }
        }

        if (phone == null)
            throw new MapperException("Не указан номер телефона!");

        Gender gender = null;

        for (String str: list) {
            String current = str.toLowerCase();
            if (current.equals("m") || current.equals("f")) {
                gender = current.equals("m") ? Gender.male : Gender.female;
                list.remove(str);
                break;
            }
        }

        if (gender == null)
            throw new MapperException("Не указан пол!");

        if (!list.get(0).matches(NAME_PATTERN))
            throw new MapperException("Фамилия содержит недопустимые символы!");

        if (!list.get(1).matches(NAME_PATTERN))
            throw new MapperException("Имя содержит недопустимые символы!");

        if (!list.get(2).matches(NAME_PATTERN))
            throw new MapperException("Отчество содержит недопустимые символы!");

        return new Person(
                toFormat(list.get(0)),
                toFormat(list.get(1)),
                toFormat(list.get(2)),
                birthday,
                phone,
                gender
        );
    }

    /**
     * Метод приведения строки к формату имени.
     * @param value Строка для приведения.
     * @return Результат приведения.
     */
    private String toFormat(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1).toLowerCase();
    }
}
