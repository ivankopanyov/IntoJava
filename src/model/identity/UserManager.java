package model.identity;

import abstractions.*;
import exceptions.IdentityException;

import java.util.UUID;

public class UserManager<TUser extends User> implements Manager<TUser> {
    private final Users<TUser> users;

    private Validator<TUser> validator;

    public UserManager() {
        users = new Users<>();
    }

    public UserManager(Validator<TUser> validator) {
        this();
        this.validator = validator;
    }

    @Override
    public Repository<TUser> get() {
        return users;
    }

    @Override
    public TUser Create(TUser user) throws IdentityException {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            throw new IdentityException();

        user.setId(null);
        user.setEmail(user.getEmail().trim().toLowerCase());

        if (validator != null) {
            ValidateResult result = validator.isValid(user);
            if (!result.isValid())
                throw new IdentityException(result.getMessage());
        }

        if (users.Contains(user))
            throw new IdentityException("Пользователь с таким email-адресом уже зарегистрирован.");

        TUser newUser = user.copy();

        do {
            String id = UUID.randomUUID().toString();
            newUser.setId(id);
        } while(!users.add(newUser));

        return newUser.copy();
    }

    @Override
    public void Change(TUser user) throws IdentityException {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            return;

        user.setEmail(user.getEmail().trim().toLowerCase());

        if (validator != null) {
            ValidateResult result = validator.isValid(user);
            if (!result.isValid())
                throw new IdentityException(result.getMessage());
        }

        TUser changedUser = users.First(u -> u.getId().equals(user.getId()));
        if (changedUser == null)
            return;

        TUser checkedUser = users.First(u -> !u.getId().equals(user.getId())
                && u.getEmail().equals(user.getEmail()));
        if (checkedUser != null)
            throw new IdentityException("Пользователь с таким email-адресом уже зарегистрирован.");

        user.setId(changedUser.getId());
        Delete(changedUser.getId());
        users.add(user);
    }

    @Override
    public void Delete(String id) {
        if (id == null || id.equals(""))
            return;

        users.delete(id);
    }

    @Override
    public TUser Check(TUser user) {
        if (user == null || user.getEmail() == null || user.getPassword() == null)
            return null;

        user.setEmail(user.getEmail().trim().toLowerCase());
        return users.check(user);
    }

    @Override
    public void setValidator(Validator<TUser> validator) {
        this.validator = validator;
    }
}
