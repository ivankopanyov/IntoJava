package abstractions;

/**
 * Результат проверки валидатора.
 */
public interface ValidateResult {

    /**
     * Метод, возвращающий результат проверки.
     * @return Результат проверки.
     */
    boolean isValid();

    /**
     * Метод, возвращающий сообщние с описанием результата проверки.
     * @return Сообщние с описанием результата проверки.
     */
    String getMessage();
}
