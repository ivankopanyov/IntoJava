package repositories.exceptions;

/**
 * Класс, описывающий исключение в работе репозитория.
 */
public class RepositoryException extends Exception {

    /**
     * Инициализация объекта исключения.
     * @param message Сообщение об ошибке.
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Инициализация объекта исключения.
     * @param message Сообщение об ошибке.
     * @param e Предыдущее исключение.
     */
    public RepositoryException(String message, Exception e) {
        super(message, e);
    }
}
