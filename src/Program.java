import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        task1();
        task2();
    }

    /**
     * Создать метод, который принимает массив int и сортирует его по последней цифре.
     * Используйте метод Arrays.sort. для его работы создайте свой компаратор.
     * Имеется в виду последняя цифра в записи числа, например в числе 123, последння цифра 3.
     * Надо сделать сортировку, которая учитывает только эту последнюю цифру в числе.
     */
    private static void task1() {
        System.out.println(" ** Сортировка массива целых чисел по последней цифре. ** \n");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int[] array = new int[random.nextInt(10, 20)];
            for (int j = 0; j < array.length; j++)
                array[j] = random.nextInt(-1000, 1000);
            System.out.println(Arrays.toString(array));
            arraySortByLastNumber(array);
            System.out.println(Arrays.toString(array));
            System.out.println();
        }
    }

    /**
     * Метод сортировки массива целых чисел по последней цифре.
     * @param array Массив для сортировки.
     */
    private static void arraySortByLastNumber(int[] array) {
        if (array == null || array.length <= 1)
            return;

        Integer[] temp = Arrays.stream(array).boxed().toArray(Integer[]::new);

        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(o1 % 10), Math.abs(o2 % 10));
            }
        });

        for (int i = 0; i < array.length; i++)
            array[i] = temp[i];
    }

    /**
     * Создайте класс, который представляет из себя коллекцию, добавьте 2 метода add и get для работы с коллекцией.
     * Реализуйте возможность использования цикла for-each для работы с данной коллекцией.
     * Для этого реализуйте интерфейс Iterable и создайте итератор
     */
    private static void task2() {
        System.out.println("\n ** Создание итерируемой коллекции. ** \n");
        Address lenina1 = new Address("ул. Ленина", "1");
        Address mira10a = new Address("пр. Мира", "10а");
        Address truda114 = new Address("пр. Труда", "114");

        Place administration = new Place("Администрация", lenina1);
        Place supermarket = new Place("Супермаркет", mira10a);
        Place coffeeHouse = new Place("Кофейня", mira10a);
        Place stadium = new Place("Стадион", truda114);
        Place tickets = new Place("Билетная касса", truda114);

        CityMap cityMap = new CityMap();
        cityMap.add(administration);
        cityMap.add(supermarket);
        cityMap.add(coffeeHouse);
        cityMap.add(stadium);
        cityMap.add(tickets);

        for (Place place: cityMap)
            System.out.println(place);

        System.out.println();
        System.out.println(Arrays.toString(cityMap.get(truda114)));

        cityMap.remove(coffeeHouse);
        cityMap.remove(supermarket);

        System.out.println();
        for (Place place: cityMap)
            System.out.println(place);
    }
}
