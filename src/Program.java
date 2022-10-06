import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    /**
     * Метод, создающий список случайной длины со случайными целыми числами в заданном диапазоне.
     * @param minSize Минимальная длина списка.
     * @param maxSize Максимальная длина списка.
     * @param minValue Минимальное значение элемента списка.
     * @param maxValue Максимальное значение элементов списка.
     * @return Список случайной длины со случайными целыми числами в заданном диапазон
     * @throws IllegalArgumentException Возбуждается, если нарушен диапазон допустимых значений.
     */
    private static List<Integer> getRandomIntList(int minSize, int maxSize, int minValue, int maxValue)
            throws IllegalArgumentException {
        if (minSize < 0)
            throw new IllegalArgumentException("Минимальное значение длины списка не должно быть меньше 0.");

        if (maxSize < minSize)
            throw new IllegalArgumentException("Мсаксимальное значение длины списка не должно быть меньше минимального.");

        if (maxValue < minValue)
            throw new IllegalArgumentException("Мсаксимальное значение элемента списка не должно быть меньше минимального.");

        if (maxSize == 0)
            return new ArrayList<Integer>(0);

        Random random = new Random();
        int size = minSize == maxSize ? minSize : random.nextInt(minSize, maxSize);
        List<Integer> numbers = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            numbers.add(minValue == maxValue ? minValue : random.nextInt(minValue, maxValue));

        return numbers;
    }

    // region Задача 1

    /**
     * Пусть дан произвольный список целых чисел, удалить из него четные числа
     */
    private static void task1() {
        System.out.println("\n ** Удаление четных чисел из списка. ** \n");
        List<Integer> numbers = getRandomIntList(10, 20, -100, 100);
        System.out.println(numbers);
        for (int i = numbers.size() - 1; i >= 0; i--)
            if (numbers.get(i) % 2 == 0)
                numbers.remove(i);
        System.out.println(numbers);
    }

    // endregion

    // region Задача 2

    /**
     * Задан целочисленный список ArrayList.
     * Найти минимальное, максимальное и среднее арифметическое из этого списка. (Collections.max())
     */
    private static void task2() {
        System.out.println("\n ** Поиск минимального, максимального и среднего арифметического значений списка. ** \n");
        List<Integer> numbers = getRandomIntList(10, 20, -100, 100);
        System.out.println(numbers);
        System.out.print("Максимальное значение: ");
        System.out.println(Collections.max(numbers));
        System.out.print("Минимальное значение: ");
        System.out.println(Collections.min(numbers));
        System.out.print("Среднее арифметическое: ");
        System.out.println(numbers.size() == 0 ? 0 : numbers.stream().reduce(0, Integer::sum) / (double)numbers.size());
    }

    // endregion

    // region Задача 3

    /**
     * Реализовать алгоритм сортировки слиянием
     */
    private static void task3() {
        System.out.println("\n ** Мердж сорт. ** \n");
        List<Integer> list = getRandomIntList(10, 20, -100, 100);
        System.out.println(list);

        mergeSort(list);
        System.out.println(list);
        System.out.println();
    }

    /**
     * Метод сортировки переданного списка целых чисел слиянием.
     * @param list Список для сортировки.
     */
    private static void mergeSort(List<Integer> list) {
        sortList(list, 0, list.size() - 1);
    }

    /**
     * Рекурсивный метод разделения и слияния списка.
     * @param list Список для сортировки
     * @param left Начальный индекс.
     * @param right Конечный индекс.
     */
    private static void sortList(List<Integer> list, int left, int right) {
        if (left < right)
        {
            int middle = left + (right - left) / 2;
            sortList(list, left, middle);
            sortList(list, middle + 1, right);
            mergeList(list, left, middle, right);
        }
    }

    /**
     * Метод разделения и сортировки списка.
     * @param list Список для сортировки.
     * @param left Индекс начального элемента.
     * @param middle Индекс среднего элемента.
     * @param right Индекс конечного элемента.
     */
    public static void mergeList(List<Integer> list, int left, int middle, int right) {
        List<Integer> leftList = new ArrayList<>(middle - left + 1);
        for (int i = 0; i < middle - left + 1; i++)
            leftList.add(list.get(left + i));

        List<Integer> rightList = new ArrayList<>(right - middle);
        for (int i = 0; i < right - middle; i++)
            rightList.add(list.get(middle + i + 1));

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftList.size() && j < rightList.size())
            list.set(k++, leftList.get(i) <= rightList.get(j) ? leftList.get(i++) : rightList.get(j++));

        while (i < leftList.size())
            list.set(k++, leftList.get(i++));

        while (j < rightList.size())
            list.set(k++, rightList.get(j++));
    }

    // endregion
}
