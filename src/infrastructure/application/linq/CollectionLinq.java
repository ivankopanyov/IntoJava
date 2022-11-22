package infrastructure.application.linq;

import java.util.*;

public class CollectionLinq<T> implements ObjectLinq<Collection<T>, T>  {

    private final Collection<T> collection;

    CollectionLinq(Collection<T> collection) {
        this.collection = collection;
    }

    public Collection<T> get() {
        return collection;
    }

    public CollectionLinq<T> orderBy(Expression<T, Integer> predicate) {
        List<T> result = new ArrayList<>(collection);
        result.sort((o1, o2) -> {
            if (Objects.equals(predicate.func(o1), predicate.func(o2)))
                return 0;
            return predicate.func(o1) < predicate.func(o2) ? 1 : -1;
        });

        return new CollectionLinq<>(result);
    }

    public T first(Expression<T, Boolean> predicate) {
        for (T item: collection)
            if (Boolean.TRUE.equals(predicate.func(item)))
                return item;

        return null;
    }

    public CollectionLinq<T> where(Expression<T, Boolean> predicate) {
        Collection<T> items = new HashSet<>();
        for (T item: collection)
            if (Boolean.TRUE.equals(predicate.func(item)))
                items.add(item);

        return new CollectionLinq<>(items);
    }

    public int count() {
        return collection.size();
    }
}

