package infrastructure.application.linq;

import java.util.*;

/**
 * Класс, описывающий Linq контейнер для колекции.
 * @param <T> Тип объектов коллекции.
 */
public class CollectionLinq<T> implements ObjectLinq<Collection<T>, T>  {

    /**
     * Объект коллекции, содержащийся в контейнере.
     */
    private final Collection<T> collection;

    /**
     * Инициализация объекта контейнера.
     * @param collection Коллекция для добавления в контейнер.
     */
    CollectionLinq(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * Метод возвращающий коллекцию, хранящуюся в контейнере.
     * @return Коллекция, хранящаяся в контейнере.
     */
    @Override
    public Collection<T> get() {
        return collection;
    }

    /**
     * Метод сортировки.
     * @param predicate Условие.
     * @return Контейнер с результатом сортировки.
     */
    @Override
    public CollectionLinq<T> orderBy(Expression<T, Integer> predicate) {
        List<T> result = new ArrayList<>(collection);
        result.sort((o1, o2) -> {
            if (Objects.equals(predicate.func(o1), predicate.func(o2)))
                return 0;
            return predicate.func(o1) < predicate.func(o2) ? 1 : -1;
        });

        return new CollectionLinq<>(result);
    }

    /**
     * Метод поиска первого вхождения, удовлетворяющего условию.
     * @param predicate Условие.
     * @return Результат поиска.
     */
    @Override
    public T first(Expression<T, Boolean> predicate) {
        for (T item: collection)
            if (Boolean.TRUE.equals(predicate.func(item)))
                return item;

        return null;
    }

    /**
     * Метод поиска всех вхождений, удовлетворяющих условию.
     * @param predicate Условие.
     * @return Результат поиска.
     */
    @Override
    public CollectionLinq<T> where(Expression<T, Boolean> predicate) {
        Collection<T> items = new HashSet<>();
        for (T item: collection)
            if (Boolean.TRUE.equals(predicate.func(item)))
                items.add(item);

        return new CollectionLinq<>(items);
    }

    /**
     * Проверка наличия элемента в коллекции.
     * @param value Значение для проверки.
     * @return Результат проверки.
     */
    @Override
    public boolean contains(T value) {
        return first(item -> item.equals(value)) != null;
    }

    /**
     * Метод, возвращающий колличество элементов в коллекции.
     * @return Колличество элементов в коллекции.
     */
    @Override
    public int count() {
        return collection.size();
    }
}

