package infrastructure.application.linq;

import abstractions.Func;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Linq {

    private Linq() { }

    public static <T> ArrayLinq<T> getObject(T[] array) {
        return new ArrayLinq<>(array);
    }

    public static <T> CollectionLinq<T> getObject(Collection<T> collection) {
        return new CollectionLinq<>(collection);
    }
}
