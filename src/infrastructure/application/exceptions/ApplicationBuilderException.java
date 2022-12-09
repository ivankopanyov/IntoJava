package infrastructure.application.exceptions;

/**
 * Класс описывающий исключение сборки приложения.
 */
public class ApplicationBuilderException extends Exception {

    /**
     * Инициализация класса исключения сборки приложения.
     * @param message Сообщение об ошибке.
     */
    public ApplicationBuilderException(String message) {
        super(message);
    }

    /**
     * Инициализация класса исключения сборки приложения.
     * @param e Предыдущее исключение.
     */
    public ApplicationBuilderException(Exception e) {
        super(e);
    }
}
