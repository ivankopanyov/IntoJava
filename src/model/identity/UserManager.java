package model.identity;

import abstractions.*;
import exceptions.IdentityException;

import java.util.UUID;

/**
 * Менеджер для работы с объектами пользователей.
 * @param <TUser> Тип объектов пользователей.
 */
public class UserManager<TUser extends User> implements Manager<TUser> {

    /**
     * Репозиторий пользователей.
     */
    private final Users<TUser> users;

    /**
     * Валидатор.
     */
    private Validator<TUser> validator;

    /**
     * Инициализация объекта менеджера.
     */
    public UserManager() {
        users = new Users<>();
    }

    /**
     * Инициализация объекта менеджера.
     * @param validator Объект валидатора.
     */
    public UserManager(Validator<TUser> validator) {
        this();
        this.validator = validator;
    }

    /**
     * Метод, возвращающий репозиторий, хранящий объекты пользователей.
     * @return Репозиторий, хранящий объекты пользователей.
     */
    @Override
    public Repository<TUser> get() {
        return users;
    }

    /**
     * Метод создания объекта пользователя.
     * @param user Значение объекта пользователя.
     * @return Созданный объект пользователя.
     * @throws IdentityException Возбуждается, если не удалось создать объект пользователя.
     */
    @Override
    public TUser create(TUser user) throws IdentityException {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            throw new IdentityException();

        user.setId(null);
        user.setEmail(user.getEmail().trim().toLowerCase());

        if (validator != null) {
            ValidateResult result = validator.isValid(user);
            if (!result.isValid())
                throw new IdentityException(result.getMessage());
        }

        if (users.contains(user))
            throw new IdentityException("Пользователь с таким email-адресом уже зарегистрирован.");

        TUser newUser = user.copy();
        newUser.setPassword(user.getPassword());

        do {
            String id = UUID.randomUUID().toString();
            newUser.setId(id);
        } while(!users.add(newUser));

        return newUser.copy();
    }

    /**
     * Метод обновления объекта пользователя.
     * @param user Значение объекта пользователя.
     * @throws IdentityException Возбуждается, если не удалось обновить объект пользователя.
     */
    @Override
    public void update(TUser user) throws IdentityException {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            return;

        user.setEmail(user.getEmail().trim().toLowerCase());

        if (validator != null) {
            ValidateResult result = validator.isValid(user);
            if (!result.isValid())
                throw new IdentityException(result.getMessage());
        }

        TUser changedUser = users.first(u -> u.getId().equals(user.getId()));
        if (changedUser == null)
            return;

        TUser checkedUser = users.first(u -> !u.getId().equals(user.getId())
                && u.getEmail().equals(user.getEmail()));
        if (checkedUser != null)
            throw new IdentityException("Пользователь с таким email-адресом уже зарегистрирован.");

        user.setId(changedUser.getId());
        delete(changedUser.getId());
        users.add(user);
    }

    /**
     * Метод удаления объекта пользователя.
     * @param id Идентификатор удаляемого объекта пользователя.
     */
    @Override
    public void delete(String id) {
        if (id == null || id.equals(""))
            return;

        users.delete(id);
    }

    /**
     * Метод проверки объекта пользователя.
     * @param user Объект пользователя для сравнения.
     */
    @Override
    public boolean check(TUser user) {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            return false;

        user.setEmail(user.getEmail().trim().toLowerCase());
        return users.check(user);
    }

    /**
     * Метод изменения валидатора.
     * @param validator Объект валтдатора.
     */
    @Override
    public void setValidator(Validator<TUser> validator) {
        this.validator = validator;
    }
}
