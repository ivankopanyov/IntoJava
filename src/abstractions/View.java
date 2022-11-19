package abstractions;

/**
 * Представление.
 */
public interface View {

    /**
     * Метод ввода значений пользователем.
     * @param titles Описания вводимых значений.
     * @return Введенный значения.
     */
    String[] input(String[] titles);

    /**
     * Метод выбора пункта из списка.
     * @param titles Список.
     * @return Индекс выбранного пукта.
     */
    int select(String[] titles);

    /**
     * Метод вывода сообщения.
     * @param message Сообщение.
     */
    void print(String message);
}
