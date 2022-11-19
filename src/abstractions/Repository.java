package abstractions;

import java.util.Collection;

public interface Repository<T> extends Iterable<T> {
    T First(Func<T, Boolean> predicate);

    Collection<T> Where(Func<T, Boolean> predicate);

    boolean Contains(T value);
}