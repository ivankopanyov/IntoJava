package abstractions;

/**
 * Интерфейс для определения условия поиска по коллекции.
 * @param <TValue> Тип объектов в списке.
 * @param <TResult> Тип результата.
 */
@FunctionalInterface
public interface Func<TValue, TResult> {
    TResult isEquals(TValue value);
}
