package services.exceptions;

/**
 * Класс, описывающий исключение преобразования объектов.
 */
public class MapperException extends Exception {

    /**
     * Инициализация объекта маппера.
     * @param message Сообщение об ошибке.
     */
    public MapperException(String message) {
        super(message);
    }

    /**
     * Инициализация объекта маппера.
     * @param message Сообщение об ошибке.
     * @param e Предыдущее исключение.
     */
    public MapperException(String message, Exception e) {
        super(message, e);
    }
}