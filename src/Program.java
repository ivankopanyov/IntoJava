import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Program {

    /**
     * Константа, хранящая колличество задач.
     */
    private static final int TASK_COUNT = 5;

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder().append('\n');
        for(int i = 1; i <= TASK_COUNT; i++)
            stringBuilder
                    .append(i)
                    .append(" >> Задача ")
                    .append(i)
                    .append('\n');

        String message = stringBuilder.append("\n0 >> Выход\n\nУкажите номер задачи: ").toString();

        while (true) {
            int number = inputNumber(message, 0, 5);

            switch (number) {
                case 0: return;
                case 1: task1(); break;
                case 2: task2(); break;
                case 3: task3(); break;
                case 4: task4(); break;
                case 5: task5(); break;
            }
        }
    }

    //region Методы пользавотельского ввода

    /**
     * Метод ввода целого числа в консоли.
     * @param message Сообщение для пользователя.
     * @return Введенное число.
     */
    private static int inputNumber(String message) throws IOException {
        return inputNumber(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Метод ввода целого числа в консоли в заданном диапазоне.
     * @param message Сообщение для пользователя.
     * @param min Минимальное допустимое значение.
     * @return Введенное число.
     */
    private static int inputNumber(String message, int min) throws IOException {
        return inputNumber(message, min, Integer.MAX_VALUE);
    }

    /**
     * Метод ввода целого числа в консоли в заданном диапазоне.
     * @param message Сообщение для пользователя.
     * @param min Минимальное допустимое значение.
     * @param max Максимальное допустимое значение.
     * @return Введенное число.
     */
    private static int inputNumber(String message, int min, int max) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            System.out.print(message);
            try {
                int number = Integer.parseInt(reader.readLine());
                if (number >= min && number <= max)
                    return number;
            } catch (NumberFormatException ignored) { }
            System.out.println("Некорректный ввод! Повторите попытку...");
        }
    }

    /**
     * Метод ввода нескольких чисел в консоли.
     * @param message Сообщение для пользователя.
     * @param separator Строка для разделения чисел.
     * @return Массив, содержвщий введенные числа.
     */
    private static int[] inputNumbers(String message, String separator) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            System.out.print(message);
            var symbols = reader.readLine().trim().split(separator);
            if (symbols.length == 0)
            {
                System.out.println("Некорректный ввод! Повторите попытку...");
                continue;
            }
            int[] numbers = new int[symbols.length];
            try {
                for (int i = 0; i < symbols.length; i++)
                    numbers[i] = Integer.parseInt(symbols[i]);
                return numbers;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Повторите попытку...");
            }
        }
    }

    /**
     * Метод ввода нескольких чисел в консоли через пробел.
     * @param message Сообщение для пользователя.
     * @return Массив, содержвщий введенные числа.
     */
    private static int[] inputNumbers(String message) throws IOException {
        return inputNumbers(message, " ");
    }

    //endregion

    //region Задача 1

    /**
     * Условие:
     * В консоли запросить имя пользователя.
     * В зависимости от текущего времени, вывести приветствие вида
     * • "Доброе утро, Имя!", если время от 05:00 до 11:59;
     * • "Добрый день, Имя!", если время от 12:00 до 17:59;
     * • "Добрый вечер, Имя!", если время от 18:00 до 22:59;
     * • "Доброй ночи, Имя!", если время от 23:00 до 4:59
     */
    private static void task1() throws IOException {
        System.out.println("\n ** Вывод приветствия в зависимости от времени суток. ** \n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        while (Objects.equals(name, ""))
        {
            System.out.print("Укажите Ваше имя: ");
            name = reader.readLine().trim();
            if (Objects.equals(name, ""))
                System.out.println("Имя не должно быть пустым! Повторите попытку...");
        }
        DateFormat dateFormat = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(dateFormat.format(new Date()));
        String timeOfDay;
        if (hour >= 5 && hour < 12)
            timeOfDay = "Доброе утро";
        else if (hour >= 12 && hour < 18)
            timeOfDay = "Добрый день";
        else if (hour >= 18 && hour < 23)
            timeOfDay = "Добрый день";
        else
            timeOfDay = "Доброй ночи";

        String output = String.format("%s, %s!", timeOfDay, name);
        System.out.println(output);
    }

    //endregion

    //region Задача 2

    /**
     * Условие:
     * Дан массив двоичных чисел, например [1,1,0,1,1,1],
     * вывести максимальное количество подряд идущих 1
     */
    private static void task2() throws IOException {
        System.out.println("\n ** Подсчет максимального количество подряд идущих чисел с заданным значением. ** \n");
        int[] numbers = inputNumbers("Укажите целые числа через пробел: ");
        int number = inputNumber("Укажите число для поиска: ");
        String output = String.format("Результат: %s", calcMaxRepeatLength(numbers, number));
        System.out.println(output);
    }

    /**
     * Метод подсчета колличества подряд идущих элементов, равных переданному значению.
     * @param numbers Массив целых чисел.
     * @param findNumber Значение искомого элемента.
     * @return Колличество подряд идущих элементов, равных переданному значению
     */
    private static int calcMaxRepeatLength(int[] numbers, int findNumber)
    {
        if (numbers == null || numbers.length == 0)
            return 0;

        int max = 0;
        int counter = 0;
        for (int n: numbers)
        {
            if (n == findNumber) {
                counter++;
                if (counter > max)
                    max = counter;
            } else
                counter = 0;
        }
        return max;
    }

    //endregion

    //region Задача 3

    /**
     * Условие:
     * Дан массив nums = [3,2,2,3] и число val = 3.
     * Если в массиве есть числа, равные заданному, нужно перенести эти элементы в конец массива.
     * Таким образом, первые несколько (или все) элементов массива должны быть отличны от заданного,
     * а остальные - равны ему.
     */
    private static void task3() throws IOException {
        System.out.println("\n ** Перенос всех элементов с заданным значением в конец массива. ** \n");
        int[] numbers = inputNumbers("Укажите целые числа через пробел: ");
        int number = inputNumber("Укажите число для переноса: ");
        int[] result = transferNumber(numbers, number);
        String output = String.format("Результат: %s", Arrays.toString(result));
        System.out.println(output);
    }

    /**
     * Метод перестановки всех элементов массива, равных переданному значению в конец массива.
     * @param numbers Массив целых чисел для перестановки.
     * @param transferredNumber Значение перстанвливаемых элементов.
     * @return Массив с результатом перестановки.
     */
    private static int[] transferNumber(int[] numbers, int transferredNumber)
    {
        if (numbers == null || numbers.length == 0)
            return numbers;

        var result = new int[numbers.length];

        for (int i = 0, j = 0, k = numbers.length - 1; i < numbers.length; i++)
            if (numbers[i] != transferredNumber)
                result[j++] = numbers[i];
            else
                result[k--] = transferredNumber;

        return result;
    }

    //endregion

    //region Задача 4

    /**
     * Условие:
     * Вычислить n-ое треугольное число
     */
    private static void task4() throws IOException {
        System.out.println("\n ** Вывод треугольного числа с заданным индексом. ** \n");
        int number = inputNumber("Укажите номер треугольного числа (> 0): ", 0);
        String output = String.format("Результат: %s", findTriangleNumber(number));
        System.out.println(output);
    }

    /**
     * Метод поиска треугольного числа по заданному индексу.
     * @param index Индекс треугольного числа.
     * @return Треуголное число, расположенное по заданному индексу.
     * Если передан отрицательны индекс, вернет -1.
     */
    private static int findTriangleNumber(int index)
    {
        if (index < 0)
            return -1;
        int number = 0;
        for (int i = 0; i <= index; i++)
            number += i;
        return number;
    }

    //endregion

    //region Задача 5

    /**
     * Условие:
     * Задано уравнение вида q + w = e, q, w, e >= 0.
     * Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
     * Требуется восстановить выражение до верного равенства.
     * Предложить хотя бы одно решение или сообщить, что его нет.
     */
    private static void task5() throws IOException {
        System.out.println("\n ** Поиск пропущенных цифр в равенстве. ** \n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^[0-9?]{1,}[ ]{0,}[+]{1}[ ]{0,}[0-9?]{1,}[ ]{0,}=[ ]{0,}[0-9?]{1,}$";
        Pattern pattern = Pattern.compile(regex);
        String exp = null;
        while (exp == null)
        {
            System.out.print("Введите выражение типа q? + ?w = e: ");
            String input = reader.readLine().trim();
            if (input.contains("?") && pattern.matcher(input).find())
                exp = input;
            else
                System.out.println("Некорректный ввод! Повторите попытку...");
        }

        var expArr = exp.split("[ ]{0,}[+=]{1}[ ]{0,}");
        List<Integer> digits = new ArrayList<>();
        var sum = 0;

        for (int i = 0; i < expArr.length; i++)
            for (int j = 0; j < expArr[i].length(); j++)
            {
                var digit = (int)Math.pow(10, expArr[i].length() - j - 1);
                if (expArr[i].charAt(j) == '?')
                    digits.add(i < expArr.length - 1 ? digit : -digit);
                else
                {
                    var n = Integer.parseInt(String.valueOf(expArr[i].charAt(j))) * digit;
                    sum += i < expArr.length - 1 ? -n : n;
                }
            }

        var passesArrays = findPasses(digits, sum);
        if (passesArrays.size() == 0)
        {
            System.out.println("Решений не найдено!");
            return;
        }

        for (char[] passes: passesArrays)
        {
            var expChars = exp.toCharArray();
            for (int i = 0, counter = 0; i < expChars.length; i++)
                if (expChars[i] == '?')
                {
                    expChars[i] = passes[counter];
                    counter++;
                }
            System.out.println(new String(expChars));
        }
    }

    /**
     * Метод поиска поиска пропущенных чисел.
     * @param digits Список, хранящий разряды пропущенных чисел.
     * @param sum Сумма всех известных чисел.
     * @return Список с массивами пропущенных символов.
     */
    private static List<char[]> findPasses(List<Integer> digits, int sum) {
        return findPasses(digits, sum, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Рекурсивный метод поиска поиска пропущенных чисел.
     * @param digits Список, хранящий разряды пропущенных чисел.
     * @param sum Сумма всех известных чисел.
     * @param numbers Список, хранящий текущий проверяемый ряд чисел.
     * @param result Список, хранящий массивы с результатами поиска.
     * @return Список с массивами пропущенных символов.
     */
    private static List<char[]> findPasses(List<Integer> digits, int sum, List<Integer> numbers, List<char[]> result) {
        if (digits == null || digits.size() == 0)
            return Collections.emptyList();

        if (numbers == null)
            numbers = Collections.emptyList();

        if (result == null)
            result = Collections.emptyList();

        List<Integer> digitsCopy = new ArrayList<>(digits);
        var digit = digitsCopy.get(0);
        digitsCopy.remove(0);
        for (int i = 0; i < 10; i++)
        {
            List<Integer> numbersCopy = new ArrayList<>(numbers);
            numbersCopy.add(i * digit);

            if (digitsCopy.size() == 0)
            {
                if (numbersCopy.stream().reduce(0, Integer::sum) == sum)
                {
                    var res = new char[numbersCopy.size()];
                    for (int j = 0; j < numbersCopy.size(); j++)
                        res[j] = String.valueOf(numbersCopy.get(j)).charAt(numbersCopy.get(j) >= 0 ? 0 : 1);
                    result.add(res);
                }
            }
            else
                findPasses(digitsCopy, sum, numbersCopy, result);
        }
        return result;
    }

    //endregion
}
