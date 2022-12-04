/**
 * Класс, описывающий исключение пустой строки.
 */
public class EmptyLineException extends RuntimeException {

    /**
     * Инициализация объекта исключения.
     * @param message Сообщение об ошибке.
     */
    public EmptyLineException(String message) {
        super(message);
    }
}
