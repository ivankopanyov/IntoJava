package infrastructure.application.exceptions;

/**
 * Класс, описывающий исключение в работе обработчика объектов презентера.
 */
public class PresenterHandlerException extends Exception {

    /**
     * Инициализация объекта исключения.
     * @param message Сообщение об ошибке.
     */
    public PresenterHandlerException(String message) {
        super(message);
    }
}
