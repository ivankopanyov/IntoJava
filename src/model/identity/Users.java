package model.identity;

import abstractions.Func;
import abstractions.Repository;
import abstractions.User;

import java.util.*;

class Users<TUser extends User> implements Repository<TUser> {

    private final Set<TUser> users;

    Users() {
        users = new HashSet<>();
    }

    @Override
    public TUser First(Func<TUser, Boolean> predicate) {
        for (TUser user: this)
            if (Boolean.TRUE.equals(predicate.isEquals(user)))
                return user.copy();

        return null;
    }

    @Override
    public Collection<TUser> Where(Func<TUser, Boolean> predicate) {
        Collection<TUser> result = new HashSet<>();
        for (TUser user: this)
            if (Boolean.TRUE.equals(predicate.isEquals(user)))
                result.add(user.copy());

        return result;
    }

    @Override
    public boolean Contains(TUser user) {
        return First(u -> u.equals(user)) != null;
    }

    @Override
    public Iterator<TUser> iterator() {
        return new UsersIterator();
    }

    boolean add(TUser user) {
        if (Contains(user))
            return false;

        users.add(user);
        return true;
    }

    void delete(String id) {
        TUser user = First(u -> Objects.equals(u.getId(), id));
        if (user == null)
            return;

        users.remove(user);
    }

    TUser check (TUser user) {
        for (TUser u: this)
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                return u.copy();

        return null;
    }

    public class UsersIterator implements Iterator<TUser> {

        private int index;
        private final List<TUser> usersList;

        public UsersIterator() {
            index = -1;
            usersList = new ArrayList<>(users);
        }

        @Override
        public boolean hasNext() {
            return ++index < usersList.size();
        }

        @Override
        public TUser next() {
            return usersList.get(index).copy();
        }
    }
}
