package view.implement;

import view.View;

import java.util.Scanner;

/**
 * Класс, описывающий консольное представление.
 */
public class MainView implements View {

    /**
     * Экземпляр сканера.
     */
    private final Scanner scanner;

    /**
     * Инициализация объекта представления.
     */
    public MainView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Метод выбора пункта меню.
     * @param title Пояснение.
     * @param items Пункты меню.
     * @return Индекс выбранного пункта.
     */
    @Override
    public int select(String title, String[] items) {
        while (true) {
            for (int i = 0; i < items.length; i++)
                System.out.println((i + 1) + ". " + items[i]);

            System.out.print('\n' + title);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input) - 1;
                if (number < 0 || number >= items.length)
                    System.out.println("\nПункта с указанным индексом не существует! Повторите попытку...\n");
                else
                    return number;
            } catch (NumberFormatException e) {
                System.out.println("\nНекорректный ввод! Повторите попытку...\n");
            }
        }
    }

    /**
     * Ввод текста пользователем.
     * @param title Пояснение.
     * @return Введенный текст.
     */
    @Override
    public String input(String title) {
        if (title != null)
            System.out.print(title);

        return scanner.nextLine();
    }

    /**
     * Вывод сообщения.
     * @param message Текст сообщения.
     */
    @Override
    public void print(String message) {
        System.out.println('\n' + message + '\n');
    }
}
