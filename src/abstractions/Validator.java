package abstractions;

public interface Validator<T> {
    ValidateResult isValid(T value);
}
