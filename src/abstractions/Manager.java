package abstractions;

import exceptions.IdentityException;

/**
 * Менеджер для работы с объектами.
 * @param <T> Тип объектов.
 */
public interface Manager<T> {

    /**
     * Метод, возвращающий репозиторий, хранящий объекты.
     * @return Репозиторий, хранящий объекты.
     * @param <TRepository> Тип репозитория.
     */
    <TRepository extends Repository<T>> TRepository get();

    /**
     * Метод создания объекта.
     * @param value Значение объекта.
     * @return Созданный объект.
     * @throws IdentityException Возбуждается, если не удалось создать объект.
     */
    T create(T value) throws IdentityException;

    /**
     * Метод обновления объекта.
     * @param value Значение объекта.
     * @throws IdentityException Возбуждается, если не удалось обновить объект.
     */
    void update(T value) throws IdentityException;

    /**
     * Метод удаления объекта.
     * @param id Идентификатор удаляемого объекта.
     */
    void delete(String id);

    /**
     * Метод проверки объекта.
     * @param value Объект для сравнения.
     */
    boolean check(T value);

    /**
     * Метод изменения валидатора.
     * @param validator Объект валтдатора.
     */
    void setValidator(Validator<T> validator);
}
