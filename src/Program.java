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
        for (int i = 0; i < 20; i++)
            numbers.add(minValue == maxValue ? minValue : random.nextInt(minValue, maxValue));

        return numbers;
    }

    // endregion


}
