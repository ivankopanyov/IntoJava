package infrastructure.application.linq;

import java.util.Collection;

/**
 * Статический класс расширения методов коллекций.
 */
public class Linq {

    /**
     * Приватный конструктор класса.
     */
    private Linq() { }

    /**
     * Метод создания Linq контейнера для массива.
     * @param array Массив для добавления в контейнер.
     * @return Контейнер.
     * @param <T> Тип объектов массива.
     */
    public static <T> ArrayLinq<T> getObject(T[] array) {
        return new ArrayLinq<>(array);
    }

    /**
     * Метод создания Linq контейнера для коллекции.
     * @param collection Коллекция для добавления в контейнер.
     * @return Контейнер.
     * @param <T> Тип объектов массива.
     */
    public static <T> CollectionLinq<T> getObject(Collection<T> collection) {
        return new CollectionLinq<>(collection);
    }
}
