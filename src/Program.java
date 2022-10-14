import java.util.*;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    //region Задание 1

    /**
     * Задание:
     * Реализуйте структуру телефонной книги с помощью HashMap, учитывая,
     * что 1 человек может иметь несколько телефонов.
     */
    private static void task1() {
        Phonebook phonebook = new Phonebook();
        int ivanovId = phonebook.addPhone("Иван Иванов", "8 (900) 000-00-00");
        phonebook.addPhone(ivanovId, "8 (911) 111-11-11");
        int petrovId = phonebook.addPhone("Петр Петров", "8 (922) 222-22-22");
        phonebook.addPhone(petrovId, "8 (933) 333-33-33");
        int smirnovaId = phonebook.addPhone("Ольга Смирнова", "8 (944) 444-44-44");
        phonebook.addPhone(smirnovaId, "8 (955) 555-55-55");
        printPhonebook(phonebook);

        phonebook.removePhone("8 (955) 555-55-55");
        phonebook.addPhone(petrovId, "8 (944) 444-44-44");
        phonebook.addPhone("Анна Никитина", "8 (911) 111-11-11");
        printPhonebook(phonebook);

    }

    /**
     * Метод вывода телефонной книги в консоль.
     * @param phonebook Объект телефонной книги.
     */
    private static void printPhonebook(Phonebook phonebook) {
        System.out.println("------------------- Телефонная книга -------------------\n");
        HashMap<Integer, String> persons = phonebook.getPersons();
        for (int id: persons.keySet()) {
            System.out.println(persons.get(id));
            for (String phone: phonebook.getPhones(id)) {
                System.out.print('\t');
                System.out.println(phone);
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------\n");
    }

    //endregion

    //region Задание 2

    /**
     * Задание:
     * Пусть дан список сотрудников.
     * Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
     * Отсортировать по убыванию популярности.
     */
    private static void task2() {
        System.out.println("\n ** Поиск повторяющихся имен сотрудников с колличеством повторений. ** \n");

        String[] employees = new String[] { "Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина",
                "Анна Крутова", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова",
                "Марина Светлова", "Мария Савина", "Мария Рыкова", "Марина Лугова", "Анна Владимирова",
                "Иван Мечников", "Петр Петин", "Иван Ежов" };

        System.out.println(Arrays.toString(employees));

        Map<String, Integer> names = new HashMap<>();

        for (String employee: employees) {
            String name = employee.split(" ")[0];
            names.put(name, names.containsKey(name) ? names.get(name) + 1 : 1);
        }

        Map<String, Integer> sortedNames = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {
                int compare = names.get(key2).compareTo(names.get(key1));
                return compare == 0 ? 1 : compare;
            }
        });
        sortedNames.putAll(names);

        System.out.println(sortedNames);
    }

    //endregion

    //region Задание 3

    /**
     * Реализовать алгоритм пирамидальной сортировки (HeapSort)
     */
    private static void task3() {
        System.out.println("\n ** Пирамидальная сортировка массива. ** \n");

        int[] numbers = generateIntArray(10, 20, -100, 100);
        System.out.println(Arrays.toString(numbers));
        heapSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    /**
     * Метод, создающий массив случайной длины со случайными целыми числами в заданном диапазоне.
     * @param minSize Минимальная длина массива.
     * @param maxSize Максимальная длина массива.
     * @param minValue Минимальное значение элемента массива.
     * @param maxValue Максимальное значение элементов массива.
     * @return Массив случайной длины со случайными целыми числами в заданном диапазон
     * @throws IllegalArgumentException Возбуждается, если нарушен диапазон допустимых значений.
     */
    private static int[] generateIntArray(int minSize, int maxSize, int minValue, int maxValue)
            throws IllegalArgumentException {
        if (minSize < 0)
            throw new IllegalArgumentException("Минимальное значение длины массива не должно быть меньше 0.");

        if (maxSize < minSize)
            throw new IllegalArgumentException("Мсаксимальное значение длины массива не должно быть меньше минимального.");

        if (maxValue < minValue)
            throw new IllegalArgumentException("Мсаксимальное значение элемента массива не должно быть меньше минимального.");

        if (maxSize == 0)
            return new int[0];

        Random random = new Random();
        int size = minSize == maxSize ? minSize : random.nextInt(minSize, maxSize);
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++)
            numbers[i] = minValue == maxValue ? minValue : random.nextInt(minValue, maxValue);

        return numbers;
    }

    /**
     * Метод пирамидальной сортировки массива целых чисел.
     * @param array Сортируемый массив.
     */
    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1)
            return;

        for (int i = array.length / 2 - 1; i >= 0; i--)
            toHeap(array, array.length, i);

        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            toHeap(array, i, 0);
        }
    }

    /**
     * Рекурсивный метод преобразования поддерева в двоичную кучу.
     * @param array Сортируемый массив.
     * @param length Длина кучи.
     * @param i Индекс корневого узла.
     */
    private static void toHeap(int[] array, int length, int i) {
        int high = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < length && array[left] > array[high])
            high = left;

        if (right < length && array[right] > array[high])
            high = right;

        if (high != i) {
            int temp = array[i];
            array[i] = array[high];
            array[high] = temp;
            toHeap(array, length, high);
        }
    }

    //endregion
}
