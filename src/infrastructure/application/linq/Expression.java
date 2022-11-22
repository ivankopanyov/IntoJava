package infrastructure.application.linq;

@FunctionalInterface
public interface Expression<TValue, TResult> {
    TResult func(TValue value);
}
