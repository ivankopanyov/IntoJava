package view;

/**
 * Представление.
 */
public interface View {

    /**
     * Метод выбора пункта меню.
     * @param title Пояснение.
     * @param items Пункты меню.
     * @return Индекс выбранного пункта.
     */
    int select(String title, String[] items);

    /**
     * Ввод текста пользователем.
     * @param title Пояснение.
     * @return Введенный текст.
     */
    String input(String title);

    /**
     * Вывод сообщения.
     * @param message Текст сообщения.
     */
    void print(String message);
}
