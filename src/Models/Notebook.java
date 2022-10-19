package Models;

/**
 * Модель, описывающая объект ноутбука.
 */
public class Notebook {

    /**
     * Производитель.
     */
    private final String manufacturer;

    /**
     * Год выпуска.
     */
    private final int year;

    /**
     * Процессор.
     */
    private final String processor;

    /**
     * Колличество ядер процессора.
     */
    private final int coresNumber;

    /**
     * Диагональ экрана.
     */
    private final int diagonal;

    /**
     * Объем жесткого диска.
     */
    private final int ssdVolume;

    /**
     * Объем оперативной памяти.
     */
    private final int ram;

    /**
     * Операционная система.
     */
    private final String operationSystem;

    /**
     * Цвет корпуса.
     */
    private final String color;

    /**
     * Цена.
     */
    private final int price;

    /**
     * Инициализация объекта ноутбука.
     * @param manufacturer Производитель.
     * @param model Год выпуска.
     * @param processor Процессор.
     * @param coresNumber Колличество ядер процессора.
     * @param diagonal Диагональ экрана.
     * @param ssdVolume Объем жесткого диска.
     * @param ram Объем оперативной памяти.
     * @param operationSystem Операционная система.
     * @param color Цвет корпуса.
     * @param price Цена.
     */
    public Notebook(String manufacturer,
             int model,
             String processor,
             int coresNumber,
             int diagonal,
             int ssdVolume,
             int ram,
             String operationSystem,
             String color,
             int price) {

        this.manufacturer = manufacturer;
        this.year = model;
        this.processor = processor;
        this.coresNumber = coresNumber;
        this.diagonal = diagonal;
        this.ssdVolume = ssdVolume;
        this.ram = ram;
        this.operationSystem = operationSystem;
        this.color = color;
        this.price = price;
    }

    /**
     * Метод, возвращающий производителя.
     * @return Производитель.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Метод, возвращающий год выпуска.
     * @return Год выпуска.
     */
    public int getYear() {
        return year;
    }

    /**
     * Метод, возвращающий процессор.
     * @return Процессор.
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * Метод, возвращающий колличество ядер процессора.
     * @return Колличество ядер процессора.
     */
    public int getCoresNumber() {
        return coresNumber;
    }

    /**
     * Метод, возвращающий диагональ экрана.
     * @return Диагональ экрана.
     */
    public int getDiagonal() {
        return diagonal;
    }

    /**
     * Метод, возвращающий объем жесткого диска.
     * @return Объем жествого диска.
     */
    public int getSsdVolume() {
        return ssdVolume;
    }

    /**
     * Метод, возвращающий объем оперативной памяти.
     * @return Объем оперативной памяти.
     */
    public int getRam() {
        return ram;
    }

    /**
     * Метод, возвращающий операционную систему.
     * @return Операционная система.
     */
    public String getOperationSystem() {
        return operationSystem;
    }

    /**
     * Метод, возвращающий цвет корпуса.
     * @return Цвет корпуса.
     */
    public String getColor() {
        return color;
    }

    /**
     * Метод, возвращающий цену ноутбука.
     * @return Цена ноутбука.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Переопределение метода сравнения объектов ноутбука.
     * @param obj Объект ноутбука для сравнения.
     * @return Результат сравнения.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Notebook notebook = (Notebook) obj;
        return (manufacturer.equals(notebook.manufacturer)
                && year == notebook.year
                && processor.equals(notebook.processor)
                && coresNumber == notebook.coresNumber
                && diagonal == notebook.diagonal
                && ssdVolume == notebook.ssdVolume
                && ram == notebook.ram
                && operationSystem.equals(notebook.operationSystem)
                && color.equals(notebook.color));
    }

    /**
     * Переопределение метода получения хэш-кода объекта ноутбука.
     * @return Хэш-код.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + year;
        result = 31 * result + processor.hashCode();
        result = 31 * result + coresNumber;
        result = 31 * result + diagonal;
        result = 31 * result + ssdVolume;
        result = 31 * result + ram;
        result = 31 * result + operationSystem.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}