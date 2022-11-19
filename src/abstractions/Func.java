package abstractions;

@FunctionalInterface
public interface Func<TValue, TResult> {
    TResult isEquals(TValue value);
}
