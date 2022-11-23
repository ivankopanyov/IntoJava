package infrastructure.application.linq;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Класс, описывающий Linq контейнер для массива.
 * @param <T> Тип объектов массива.
 */
public class ArrayLinq<T> implements ObjectLinq<T[], T> {

    /**
     * Объект массива, содержащийся в контейнере.
     */
    private final T[] array;

    /**
     * Инициализация объекта контейнера.
     * @param array Массив для добавления в контейнер.
     */
    ArrayLinq(T[] array) {
        this.array = array;
    }

    /**
     * Метод возвращающий массив, хранящийся в контейнере.
     * @return Массив, хранящийся в контейнере.
     */
    @Override
    public T[] get() {
        return array;
    }

    /**
     * Метод сортировки.
     * @param predicate Условие.
     * @return Контейнер с результатом сортировки.
     */
    @Override
    public ArrayLinq<T> orderBy(Expression<T, Integer> predicate) {
        T[] result = Arrays.copyOf(array, array.length);
        Arrays.sort(result, (o1, o2) -> {
            if (Objects.equals(predicate.func(o1), predicate.func(o2)))
                return 0;
            return predicate.func(o1) < predicate.func(o2) ? 1 : -1;
        });

        return new ArrayLinq<>(result);
    }

    /**
     * Метод поиска первого вхождения, удовлетворяющего условию.
     * @param predicate Условие.
     * @return Результат поиска.
     */
    @Override
    public T first(Expression<T, Boolean> predicate) {
        for (T item: array)
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
    public ArrayLinq<T> where(Expression<T, Boolean> predicate) {
        Collection<T> items = new HashSet<>();
        for (T item: array)
            if (Boolean.TRUE.equals(predicate.func(item)))
                items.add(item);

        return new ArrayLinq<>((T[]) items.toArray());
    }

    /**
     * Проверка наличия элемента в массиве.
     * @param value Значение для проверки.
     * @return Результат проверки.
     */
    @Override
    public boolean contains(T value) {
        return first(item -> item.equals(value)) != null;
    }

    /**
     * Метод, возвращающий колличество элементов в масиве.
     * @return Колличество элементов в массиве.
     */
    @Override
    public int count() {
        return array.length;
    }
}
