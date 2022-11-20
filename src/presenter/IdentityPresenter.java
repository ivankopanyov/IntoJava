package presenter;

import abstractions.Manager;
import abstractions.View;
import exceptions.IdentityException;
import model.IdentityUser;

import java.util.Collection;
import java.util.List;

/**
 * Класс, описывающий прзентер.
 */
public class IdentityPresenter {

    /**
     * Менеджер.
     */
    private final Manager<IdentityUser> manager;

    /**
     * Представление.
     */
    private final View view;

    /**
     * Текуший авторизванный пользователь.
     */
    private IdentityUser current;

    /**
     * Флаг для выхода из приложения.
     */
    private boolean isExit;

    /**
     * Инициализация объекта презентера.
     * @param manager Менеджер.
     * @param view Представление.
     * @throws IllegalArgumentException Возбуждается, если переданные параметрв не инициализированы.
     */
    public IdentityPresenter(Manager<IdentityUser> manager, View view) throws IllegalArgumentException {
        if (manager == null || view == null)
            throw new IllegalArgumentException();

        this.manager = manager;
        this.view = view;
        current = null;
        isExit = false;
    }

    /**
     * Метод запуска перезентера.
     */
    public void run() {
        while (!isExit)
            if (!manager.check(current))
                mainMenu();
            else
                accountMenu();

        view.print("Завершение работы приложения...");

    }

    /**
     * Метод вывода меню пользователя.
     */
    private void mainMenu() {
        while (!isExit && !manager.check(current)) {
            String[] titles = new String[] {
                    "Войти",
                    "Регистрация",
                    "Выход"
            };

            switch (view.select(titles)) {
                case 0 -> login();
                case 1 -> register();
                default -> isExit = true;
            }
        }
    }

    /**
     * Метод вывода меню личного кабинета.
     */
    private void accountMenu() {
        while (!isExit && manager.check(current)) {

            view.print(current.toString());

            String[] titles = new String[] {
                    "Список пользователей",
                    "Найти пользователя",
                    "Редактировать аккаунт",
                    "Выйти из аккаунта",
                    "Удалить аккаунт",
                    "Выход"
            };

            switch (view.select(titles)) {
                case 0 -> list(manager.get().all());
                case 1 -> find();
                case 2 -> edit();
                case 3 -> current = null;
                case 4 -> remove();
                default -> isExit = true;
            }
        }
    }

    /**
     * Метод регистрации пользователя.
     */
    private void register() {
        if (manager.check(current))
            return;

        String[] titles = new String[] { "Введите email: " };
        String email = view.input(titles)[0].trim();

        if (email.equals("")) {
            view.print("Email не должен быть пустым!");
            return;
        }

        String password = inputPassword();
        if (password == null)
            return;

        IdentityUser user = new IdentityUser(email, password);
        try {
            current = manager.create(user);
            current.setPassword(user.getPassword());
        } catch (IdentityException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Метод авторизации пользователя.
     */
    private void login() {
        if (manager.check(current))
            return;

        String[] titles = new String[] {
                "Введите email: ",
                "Введите пароль: "
        };

        String[] input = view.input(titles);
        input[0] = input[0].trim();

        if (List.of(input).contains("")) {
            System.out.println("Заполните все поля!");
            return;
        }

        IdentityUser user = new IdentityUser(input[0], input[1]);
        if (!manager.check(user)) {
            System.out.println("Неверный email или пароль!");
            return;
        }

        current = manager.get().first(u -> u.getEmail().equals(user.getEmail()));
        current.setPassword(user.getPassword());
    }

    /**
     * Метод вывода списка пользователей.
     * @param users Коллекция выводимых пользователей.
     */
    private void list(Collection<IdentityUser> users) {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        for (IdentityUser user: users)
            stringBuilder
                    .append(++i)
                    .append(". ")
                    .append(user)
                    .append('\n');

        view.print(stringBuilder.toString());
    }

    /**
     * Метод поиска пользователей.
     */
    private void find() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        String pattern = view.input(new String[] { "Введите паттерн для поиска: " })[0];
        Collection<IdentityUser> users = manager.get().where(user -> user.toString().contains(pattern));
        list(users);
    }

    /**
     * Метод удаления пользователя.
     */
    private void remove() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        view.print("Вы уверены, что хотите удалить аккаунт?!");
        int num = view.select(new String[] { "Да", "Нет"} );
        if (num == 0)
            manager.delete(current.getId());
    }

    /**
     * Метод изменения данных пользователя.
     */
    private void edit() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        String[] titles = new String[] {
                "Изменить пароль",
                "Изменить имя",
                "Изменить фамилию",
                "Назад"
        };

        switch (view.select(titles)) {
            case 0 -> changePassword();
            case 1 -> changeFirstName();
            case 2 -> changeLastName();
        }
    }

    /**
     * Метод изменения пароля.
     */
    private void changePassword() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        String password = inputPassword();
        if (password == null)
            return;

        IdentityUser user = current.copy();
        user.setPassword(password);
        try {
            manager.update(user);
            current.setPassword(password);
        } catch (IdentityException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Метод изменения имени.
     */
    private void changeFirstName() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        String firstName = view.input(new String[] { "Введите имя: " })[0];
        IdentityUser user = current.copy();
        user.setFirstName(firstName);
        user.setPassword(current.getPassword());

        try {
            manager.update(user);
            current.setFirstName(firstName);
        } catch (IdentityException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Метод измеения фамилии.
     */
    private void changeLastName() {
        if (!manager.check(current)) {
            current = null;
            return;
        }

        String lastName = view.input(new String[] { "Введите фамилию: " })[0];
        IdentityUser user = current.copy();
        user.setLastName(lastName);
        user.setPassword(current.getPassword());

        try {
            manager.update(user);
            current.setLastName(lastName);
        } catch (IdentityException e) {
            view.print(e.getMessage());
        }
    }

    /**
     * Метод ввода пароля.
     * @return Введенный пароль.
     */
    private String inputPassword() {

        String[] titles = new String[] {
                "Введите пароль: ",
                "Повторте пароль: "
        };

        String[] input = view.input(titles);

        if (List.of(input).contains("")) {
            view.print("Пароль не должен быть пустым!");
            return null;
        }

        if (!input[0].equals(input[1])) {
            view.print("Пароли не совпадают!");
            return null;
        }

        return input[0];
    }
}
