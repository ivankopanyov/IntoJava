import java.util.LinkedList;
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
     * Метод вывода в консоль элементов LinkedList.
     * @param list Объект LinkedList.
     * @param separator Строка-разделитель элементов.
     * @param line Перенос строки по завершении вывода.
     * @param <T> Тип элементов LinkedListю
     */
    private static <T> void printLinkedList(LinkedList<T> list, String separator, boolean line) {
        if (list == null || list.size() == 0) {
            System.out.println();
            return;
        }

        for (T value: list) {
            System.out.print(value.toString());
            if (list.getLast() != value)
                System.out.print(separator);
        }

        if (line)
            System.out.println();
    }

    /**
     * Метод, генерирующий LinkedList случайной длины в заданном диапазоне
     * со случайными значениями целых чисел в заданном диапазоне.
     * @param minSize Минимальная длина LinkedList.
     * @param maxSize Максимальная длина LinkedList.
     * @param minValue Минимальное значение элемента.
     * @param maxValue Максимальное значение элемента.
     * @return Сгенерированный объект LinkedList.
     * @throws IllegalArgumentException Возбуждается, если нарушен диапазон допустимых значений.
     */
    private static LinkedList<Integer> generateIntLinkedList(int minSize, int maxSize, int minValue, int maxValue)
            throws IllegalArgumentException {
        if (minSize < 0)
            throw new IllegalArgumentException("Минимальное значение длины списка не должно быть меньше 0.");

        if (maxSize < minSize)
            throw new IllegalArgumentException("Мсаксимальное значение длины списка не должно быть меньше минимального.");

        if (maxValue < minValue)
            throw new IllegalArgumentException("Мсаксимальное значение элемента списка не должно быть меньше минимального.");

        LinkedList<Integer> list = new LinkedList<>();

        if (maxSize == 0)
            return list;

        Random random = new Random();
        int size = minSize == maxSize ? minSize : random.nextInt(minSize, maxSize);
        for (int i = 0; i < size; i++)
            list.push(minValue == maxValue ? minValue : random.nextInt(minValue, maxValue));

        return list;
    }

    //region Задание 1

    /**
     * Задание:
     * Пусть дан LinkedList с несколькими элементами.
     * Реализуйте метод, который вернет “перевернутый” список.
     */
    private static void task1() {
        System.out.println("\n ** Переворачивание LinkedList ** \n");
        LinkedList<Integer> before = generateIntLinkedList(10, 20, -100, 100);
        printLinkedList(before, " ", true);
        LinkedList<Integer> after = revertList(before);
        printLinkedList(after, " ", true);
    }

    /**
     * Метод, возвращающий новый объект LinkedList с элементами переданного объекта LinkedList в обратном порядке.
     * @param list Объект LinkedList.
     * @return Объект LinkedList с элементами переданного объекта LinkedList в обратном порядке.
     * @param <T> Тип элементов LinkedList.
     */
    private static <T> LinkedList<T> revertList(LinkedList<T> list) {
        if (list == null)
            return null;

        if (list.size() <= 1)
            return (LinkedList)list.clone();

        LinkedList<T> revertList = new LinkedList<>();

        for (T value: list)
            revertList.add(0, value);

        return revertList;
    }

    //endregion

    //region Задание 2

    /**
     * Задание:
     * Реализуйте очередь с помощью LinkedList со следующими методами:
     * enqueue() - помещает элемент в конец очереди,
     * dequeue() - возвращает первый элемент из очереди и удаляет его,
     * first() - возвращает первый элемент из очереди, не удаляя.
     */
    private static void task2() {
        System.out.println("\n ** Реализация очереди с помощью LinkedList ** \n");

        IQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);

        while (queue.size() > 0) {
            System.out.println(queue.first());
            System.out.println(queue.dequeue());
        }
    }

    //endregion

    //region Задание 3

    /**
     * Задание:
     * Найдите сумму всех элементов LinkedList, сохраняя все элементы в списке.
     * Используйте итератор
     */
    private static void task3() {
        System.out.println("\n ** Сумма всех элементов LinkedList ** \n");
        LinkedList<Integer> list = generateIntLinkedList(10, 20, 0, 100);
        printLinkedList(list, " + ", false);
        System.out.print(" = ");
        System.out.println(calcLinkedListSum(list));

    }

    /**
     * Метод, рассчитывающий сумму всех элементов переданного объекта LinkedList.
     * @param list Объект LinkedList для рассчета суммы.
     * @return Сумма всех элементов переданного объекта LinkedList.
     */
    private static int calcLinkedListSum(LinkedList<Integer> list) {
        if (list == null || list.size() == 0)
            return 0;

        int sum = 0;
        for (int value: list)
            sum += value;

        return sum;
    }

    //endregion
}
