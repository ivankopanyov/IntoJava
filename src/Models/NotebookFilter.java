package Models;

/**
 * Модель объекта фильтра для поиска ноутбуков.
 */
public class NotebookFilter {

    /**
     * Фильтр производителя.
     */
    private String manufacturer;

    /**
     * Фильтр года выпуска.
     */
    private Range year;

    /**
     * Фильтр процессора.
     */
    private String processor;

    /**
     * Фильтр колличества ядер процессора.
     */
    private Range coresNumber;

    /**
     * Фильтр диагонали экрана.
     */
    private Range diagonal;

    /**
     * Фильтр объема жесткого диска.
     */
    private Range ssdVolume;

    /**
     * Фильтр объема оперативной памяти.
     */
    private Range ram;

    /**
     * Фильтр операционной системы.
     */
    private String operationSystem;

    /**
     * Фильтр цвета корпуса.
     */
    private String color;

    /**
     * Фильтр цены.
     */
    private Range price;

    /**
     * Метод изменения состояния фильтра производителя.
     * @param value Новая строка для фильтрации по производителю.
     */
    public void setManufacturer(String value) {
        manufacturer = value;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра производителя.
     * @return Фильтр производителя.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Метод изменени диапозона фильтрации по году выпуска.
     * @param range Диапазон года выпуска.
     */
    public void setYear(Range range) {
        year = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра года выпуска.
     * @return Фильтр года выпуска.
     */
    public Range getYear() {
        return year;
    }

    /**
     * Метод изменения состояния фильтра процессора.
     * @param value Новая строка для фильтрации по процессору.
     */
    public void setProcessor(String value) {
        processor = value;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра процессора.
     * @return Фильтр процессора.
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * Метод изменени диапозона фильтрации по колличеству ядер процессора.
     * @param range Диапазон колличества ядер процессора.
     */
    public void setCoresNumber(Range range) {
        coresNumber = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра колличества ядер процессора.
     * @return Фильтр колличества ядер процессора.
     */
    public Range getCoresNumber() {
        return coresNumber;
    }

    /**
     * Метод изменени диапозона фильтрации по размеру диагонали экрана.
     * @param range Диапазон размера диагонали экрана.
     */
    public void setDiagonal(Range range) {
        diagonal = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра диагонали экрана.
     * @return Фильтр диагонали экрана.
     */
    public Range getDiagonal() {
        return diagonal;
    }

    /**
     * Метод изменени диапозона фильтрации по объему жесткого диска.
     * @param range Диапазон объема жесткого диска.
     */
    public void setSsdVolume(Range range) {
        ssdVolume = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра объема жесткого диска.
     * @return Фильтр объема жесткого диска.
     */
    public Range getSsdVolume() {
        return ssdVolume;
    }

    /**
     * Метод изменени диапозона фильтрации по объему оперативной памяти.
     * @param range Диапазон объема оперативной памяти.
     */
    public void setRam(Range range) {
        ram = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра объема оперативной памяти.
     * @return Фильтр объема оперативной памяти.
     */
    public Range getRam() {
        return ram;
    }

    /**
     * Метод изменения состояния фильтра операционной системы.
     * @param value Новая строка для фильтрации по оперционной системе.
     */
    public void setOperationSystem(String value) {
        operationSystem = value;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра операционной системы.
     * @return Фильтр операционной системы.
     */
    public String getOperationSystem() {
        return operationSystem;
    }

    /**
     * Метод изменения состояния фильтра цвета корпуса.
     * @param value Новая строка для фильтрации по цвету корпуса.
     */
    public void setColor(String value) {
        color = value;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра цвета корпуса.
     * @return Фильтр цвета корпуса.
     */
    public String getColor() {
        return color;
    }

    /**
     * Метод изменени диапозона фильтрации по цене.
     * @param range Диапазон цены.
     */
    public void setPrice(Range range) {
        price = range;
    }

    /**
     * Метод, возвращающий текущее состояние фильтра цены ноутбука.
     * @return Фильтр цены ноутбука.
     */
    public Range getPrice() {
        return price;
    }
}
