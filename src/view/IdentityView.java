package view;

import abstractions.View;

import java.util.Scanner;

/**
 * Класс, описывающий представление.
 */
public class IdentityView implements View {

    /**
     * Сканер.
     */
    Scanner scanner;

    /**
     * Инициализация объекта представления.
     */
    public IdentityView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Метод ввода значений пользователем.
     * @param titles Описания вводимых значений.
     * @return Введенный значения.
     */
    @Override
    public String[] input(String[] titles) {

        if (titles == null || titles.length == 0)
            return new String[0];

        System.out.println();
        String[] result = new String[titles.length];
        for (int i = 0; i < titles.length; i++) {
            System.out.print(titles[i]);
            result[i] = scanner.nextLine();
        }

        return result;
    }

    /**
     * Метод выбора пункта из списка.
     * @param titles Список.
     * @return Индекс выбранного пукта.
     */
    @Override
    public int select(String[] titles) {
        if (titles == null || titles.length == 0)
            return -1;

        System.out.println();
        for (int i = 0; i < titles.length; i++)
            System.out.println((i + 1) + ". " + titles[i]);

        int num = -1;
        while (true) {
            System.out.print("Укажите номер пункта меню: ");
            String input = scanner.nextLine();
            try {
                num = Integer.parseInt(input) - 1;
                if (num >= 0 && num < titles.length)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Повторите попытку...");
            }
            System.out.println("Пункт меню с таким номером не найден! Повторите попытку...");
        }
        return num;
    }

    /**
     * Метод вывода сообщения.
     * @param message Сообщение.
     */
    @Override
    public void print(String message) {
        System.out.println();
        System.out.println(message);
    }
}
