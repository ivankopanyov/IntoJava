package exceptions;

/**
 * Класс, описывающий исключеие идентификации.
 */
public class IdentityException extends Exception {

    /**
     * Инициализация исключения.
     */
    public IdentityException() { }

    /**
     * Инициализация исключения.
     * @param message Сообщение об ошибке.
     */
    public IdentityException(String message) {
        super(message);
    }
}
