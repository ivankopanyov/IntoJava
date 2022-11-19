package abstractions;

import exceptions.IdentityException;

public interface Manager<T> {

    <TRepository extends Repository<T>> TRepository get();

    T Create(T value) throws IdentityException;

    void Change(T value) throws IdentityException;

    void Delete(String id);

    T Check(T value);

    void setValidator(Validator<T> validator);
}
