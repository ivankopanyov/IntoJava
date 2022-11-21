package abstractions;

import java.util.Collection;

/**
 * Репозиторий.
 * @param <T> Тип объектов, хранящихся в репозитории.
 */
public interface Repository<T> extends Iterable<T> {

    /**
     * Метод поиска первого вхождения валидного объекта.
     * @param predicate Выражение для поиска.
     * @return Результат поиска.
     */
    T first(Func<T, Boolean> predicate);

    /**
     * Метод поиска всех вхождений валидного объекта.
     * @param predicate Выражение для поиска.
     * @return Результат поиска.
     */
    Collection<T> where(Func<T, Boolean> predicate);

    /**
     * Метод проверки наличия объекта в репозитории.
     * @param value Объект для проверки.
     * @return Результат проверки.
     */
    boolean contains(T value);

    /**
     * Метод, возвращающий коллекцию всех объектов, хранящихся в репозитории.
     * @return Коллекция всех объектов.
     */
    Collection<T> all();
}