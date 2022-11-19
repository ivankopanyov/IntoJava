package model.identity;

import abstractions.Func;
import abstractions.Repository;
import abstractions.User;

import java.util.*;

/**
 * Класс, описывающий репозиторий пользователей.
 * @param <TUser> Тип объектов пользователей, хранящихся в репозитории.
 */
class Users<TUser extends User> implements Repository<TUser> {

    /**
     * Сет пользователей, хранящихся в репозитории.
     */
    private final Set<TUser> users;

    /**
     *  Инициализация объекта репоззитория.
     */
    Users() {
        users = new HashSet<>();
    }

    /**
     * Метод поиска первого вхождения валидного объекта пользователя.
     * @param predicate Выражение для поиска.
     * @return Результат поиска.
     */
    @Override
    public TUser first(Func<TUser, Boolean> predicate) {
        for (TUser user: this)
            if (Boolean.TRUE.equals(predicate.isEquals(user)))
                return user.copy();

        return null;
    }

    /**
     * Метод поиска всех вхождений валидного объекта пользователя.
     * @param predicate Выражение для поиска.
     * @return Результат поиска.
     */
    @Override
    public Collection<TUser> where(Func<TUser, Boolean> predicate) {
        Collection<TUser> result = new HashSet<>();
        for (TUser user: this)
            if (Boolean.TRUE.equals(predicate.isEquals(user)))
                result.add(user.copy());

        return result;
    }

    /**
     * Метод проверки наличия объекта пользователя в репозитории.
     * @param user Объект для проверки.
     * @return Результат проверки.
     */
    @Override
    public boolean contains(TUser user) {
        return first(u -> u.equals(user)) != null;
    }

    /**
     * Метод, возвращающий коллекцию всех объектов пользователей, хранящихся в репозитории.
     * @return Коллекция всех объектов пользователей.
     */
    @Override
    public Collection<TUser> all() {
        Collection<TUser> usersCollection = new HashSet<>();
        for (TUser user: this)
            usersCollection.add(user);
        return usersCollection;
    }

    /**
     * Метод, возвращающий итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<TUser> iterator() {
        return new UsersIterator();
    }

    /**
     * Метод добавления пользователя в репозиторий.
     * @param user Объект пользователя.
     * @return Результат.
     */
    boolean add(TUser user) {
        if (contains(user))
            return false;

        users.add(user);
        return true;
    }

    /**
     * Метод удаления пользователя из репозитория.
     * @param id Объект пользователя.
     */
    void delete(String id) {
        for (TUser user: users)
            if (user.getId().equals(id))
            {
                users.remove(user);
                return;
            }
    }

    /**
     * Метод проверки пользователя.
     * @param user Пользователь для проверки.
     * @return Результат проверки.
     */
    boolean check (TUser user) {
        for (TUser u: users)
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                return true;

        return false;
    }

    /**
     * Класс, описывабщий итератор репозитория пользователей.
     */
    public class UsersIterator implements Iterator<TUser> {

        /**
         * Текуший индекс.
         */
        private int index;

        /**
         * Список пользователей.
         */
        private final List<TUser> usersList;

        /**
         * Инициализация итератора.
         */
        public UsersIterator() {
            index = -1;
            usersList = new ArrayList<>(users);
        }

        /**
         * Метод проверки наличия следущего пользователя в списке.
         * @return Результат проверки.
         */
        @Override
        public boolean hasNext() {
            return ++index < usersList.size();
        }

        /**
         * Метод, возвращаюший следущий объект пользователя.
         * @return Следущий объект пользователя.
         */
        @Override
        public TUser next() {
            return usersList.get(index).copy();
        }
    }
}
