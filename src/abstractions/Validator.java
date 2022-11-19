package abstractions;

/**
 * Валидатор.
 * @param <T> Тип проверяемого объекта.
 */
public interface Validator<T> {

    /**
     * Метод проверки объекта на валидность.
     * @param value Объект для проверки.
     * @return Результатпроверки.
     */
    ValidateResult isValid(T value);
}
