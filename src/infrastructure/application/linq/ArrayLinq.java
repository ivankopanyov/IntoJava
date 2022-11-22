package infrastructure.application.linq;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class ArrayLinq<T> implements ObjectLinq<T[], T> {

    private final T[] array;

    ArrayLinq(T[] array) {
        this.array = array;
    }

    public T[] get() {
        return array;
    }

    public ArrayLinq<T> orderBy(Expression<T, Integer> predicate) {
        T[] result = Arrays.copyOf(array, array.length);
        Arrays.sort(result, (o1, o2) -> {
            if (Objects.equals(predicate.func(o1), predicate.func(o2)))
                return 0;
            return predicate.func(o1) < predicate.func(o2) ? 1 : -1;
        });

        return new ArrayLinq<>(result);
    }

    public T first(Expression<T, Boolean> predicate) {
        for (T item: array)
            if (Boolean.TRUE.equals(predicate.func(item)))
                return item;

        return null;
    }

    public ArrayLinq<T> where(Expression<T, Boolean> predicate) {
        Collection<T> items = new HashSet<>();
        for (T item: array)
            if (Boolean.TRUE.equals(predicate.func(item)))
                items.add(item);

        return new ArrayLinq<>((T[]) items.toArray());
    }

    public boolean contains(T value) {
        return first(item -> item.equals(value)) != null;
    }

    public int count() {
        return array.length;
    }
}
