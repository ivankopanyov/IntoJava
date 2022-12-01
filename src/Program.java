import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int[] arg1 = null;
            while (arg1 == null) {
                System.out.print("Укажите числа первого массива через пробел: ");
                try {
                    arg1 = stringToIntArray(scanner.nextLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод! Повторите попытку...");
                }
            }

            int[] arg2 = null;
            while (arg2 == null) {
                System.out.print("Укажите числа второго массива через пробел: ");
                try {
                    arg2 = stringToIntArray(scanner.nextLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод! Повторите попытку...");
                }
            }

            System.out.print("Разность массивов: ");
            try {
                System.out.println(Arrays.toString(differenceArray(arg1, arg2)));
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("Частное массивов: ");
            try {
                System.out.println(Arrays.toString(divideArray(arg1, arg2)));
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Метод приведение элементов передаваемого массива к типу массива контейнера.
     * @param source Массив для приведения типов элементов.
     * @param dest Массив контейнер.
     * @param <T> Тип приводимых элементов.
     * @param <U> Тип приведенных элементов.
     * @throws NullPointerException Передан неинициализированный массив.
     * @throws IndexOutOfBoundsException Длина массива dest меньше длины массива source.
     * @throws ClassCastException Неверное приведение типов.
     */
    public static <T, U> void castArrayItems(T[] source, U[] dest)
            throws NullPointerException, IndexOutOfBoundsException, ClassCastException {

        for (int i = 0; i < source.length; i++)
            dest[i] = (U) source[i];
    }

    /**
     * Метод, генерирующий массив случайных целых чисел.
     * @param length Длина массива.
     * @return Сгенерированный массив.
     * @throws NegativeArraySizeException Отрицательная длина массива.
     */
    public static int[] generateRandomIntArray(int length) throws NegativeArraySizeException {
        Random random = new Random();
        int[] result = new int[length];
        for (int i = 0; i < length; i++)
            result[i] = random.nextInt();

        return result;
    }

    /**
     * Метод приведения строки к целочисленному массиву.
     * @param str Строка для приведения.
     * @return Массив целых чисел.
     * @throws NullPointerException Переданная строка не инициализирована.
     * @throws NumberFormatException Недопустимый формат строки.
     */
    public static int[] stringToIntArray(String str)
            throws NullPointerException, NumberFormatException {

        String[] arr = str.trim().split("\\s+");
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = Integer.parseInt(arr[i]);

        return result;
    }

    /**
     * Метод вычитания элементов целочисленных массивов.
     * @param minuend Уменьшаемый массив.
     * @param subtrahend Вычитаемый массив.
     * @return Разность массивов.
     */
    public static int[] differenceArray(int[] minuend, int[] subtrahend) {

        if (minuend == null || subtrahend == null)
            throw new RuntimeException("Передан неинициализированный массив");

        if (minuend.length != subtrahend.length)
            throw new RuntimeException("Длины массивов не совпадают!");

        int[] result = new int[minuend.length];

        for (int i = 0; i < minuend.length; i++)
            result[i] = minuend[i] - subtrahend[i];

        return result;
    }

    /**
     * Метод деления элементов целочисленных массивов.
     * @param dividend Делимый массив.
     * @param divider Массив делитель.
     * @return Частное массивов.
     */
    public static int[] divideArray(int[] dividend, int[] divider) {

        if (dividend == null || divider == null)
            throw new RuntimeException("Передан неинициализированный массив");

        if (dividend.length != divider.length)
            throw new RuntimeException("Длины массивов не совпадают!");

        int[] result = new int[dividend.length];

        for (int i = 0; i < dividend.length; i++) {
            if (divider[i] == 0)
                throw new RuntimeException("Деление на ноль");

            result[i] = dividend[i] / divider[i];
        }

        return result;
    }
}
