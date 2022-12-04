import java.util.Scanner;

/**
 * Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
 * и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
 * вместо этого, необходимо повторно запросить у пользователя ввод данных.
 */
public class Task1 {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        while (true)
            System.out.println("Вы ввели: " + inputFloat());
    }

    /**
     * Метод ввода пользователем вещественного числа.
     * @return Введенное число.
     */
    public static float inputFloat() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print("Введите вещественное число: ");
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Повторите попытку...");
            }
        } while (true);
    }
}
