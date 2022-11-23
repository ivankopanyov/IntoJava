package view;

import abstractions.View;
import infrastructure.application.ViewBase;

import java.util.Scanner;

/**
 * Класс, описывающий представление.
 */
public class IdentityViewV2 extends ViewBase implements View {

    private final View view;

    /**
     * Инициализация объекта представления.
     */
    public IdentityViewV2() {
        view = new IdentityView();
    }

    /**
     * Метод ввода значений пользователем.
     * @param titles Описания вводимых значений.
     * @return Введенный значения.
     */
    @Override
    public String[] input(String[] titles) {
        return view.input(titles);
    }

    /**
     * Метод выбора пункта из списка.
     * @param titles Список.
     * @return Индекс выбранного пукта.
     */
    @Override
    public int select(String[] titles) {
        return view.select(titles);
    }

    /**
     * Метод вывода сообщения.
     * @param message Сообщение.
     */
    @Override
    public void print(String message) {
        view.print(message);
    }
}
