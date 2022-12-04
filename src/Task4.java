import java.util.Scanner;

/**
 * Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
 * Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
 */
public class Task4 {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Вы ввели: " + inputLine("Введите строку: "));
            } catch (EmptyLineException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Метод ввода пользователем строки.
     * @param title Пояснение.
     * @return Введенная строка.
     * @throws EmptyLineException Введена пустая строка.
     */
    public static String inputLine(String title) throws EmptyLineException {
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);
        String input = scanner.nextLine();
        if (input.trim().equals(""))
            throw new EmptyLineException("Строка не должна быть пустой!");
        return input;
    }
}
