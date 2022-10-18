package Services;

import Models.Notebook;

import java.util.Random;

/**
 * Класс, описывающий фабрику по созданию нойтбуков.
 */
public class NotebookBuilder {

    /**
     * Массив, храняций имена производителей.
     */
    private final String[] manufacturers = new String[] { "Apple", "Acer", "ASUS", "Dell", "HP", "Lenovo" };

    /**
     * Массив, хранящий названия процессоров.
     */
    private final String[] processors = new String[] { "AMD Ryzen ", "Intel Core i", "Apple M1" };

    /**
     * Массив, хранящий названия операционных систем.
     */
    private final String[] operationSystems = new String[] { "macOS", "Windows 10", "Windows 11", "Linux" };

    /**
     * Массив, хранящий названия цветов корпуса.
     */
    private final String[] colors = new String[] { "Белый", "Серый", "Красный", "Черный", "Синий" };

    /**
     * Метод, возвращающий случайно созданный объект ноутбука.
     * @return Случайно созданный объект ноутбука.
     */
    public Notebook getRandom() {
        Random random = new Random();

        String manufacturer = manufacturers[random.nextInt(manufacturers.length)];

        int year = random.nextInt(2015, 2023);

        String processor = manufacturer.equals(manufacturers[0]) ?
                processors[random.nextInt(1, processors.length)] :
                processors[random.nextInt(0, processors.length -1)];

        if (processor.equals(processors[0]) || processor.equals(processors[1]))
            processor += 3 + 2 * random.nextInt(4);

        int coresNumber = 2 + 2 * random.nextInt(4);

        String operationSystem = manufacturer.equals(manufacturers[0]) ?
                operationSystems[0] : operationSystems[random.nextInt(1, operationSystems.length)];

        int diagonal = random.nextInt(10, 18);

        int ssdVolume = (int)Math.pow(2, random.nextInt(7, 14));

        int ram = (int)Math.pow(2, random.nextInt(3, 7));

        String color = manufacturer.equals(manufacturers[0]) ?
                colors[random.nextInt(3)] : colors[random.nextInt(operationSystems.length)];

        int price = random.nextInt(500, 5000) * 100;

        return new Notebook(manufacturer, year, processor, coresNumber, diagonal, ssdVolume, ram,
                operationSystem, color, price);
    }
}