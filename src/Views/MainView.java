package Views;

import Controllers.NotebookController;
import Models.Notebook;
import Models.NotebookFilter;
import Models.Range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Класс, описывающий представление каталога ноутбуков в консоли.
 */
public class MainView {

    /**
     * Константа, хранящая название столбца производителей.
     */
    private static final String MANUFACTURER = "Производитель";

    /**
     * Константа, хранящая название столбца годов выпуска.
     */
    private static final String YEAR = "Год";

    /**
     * Константа, хранящая название столбца процессоров.
     */
    private static final String PROCESSOR = "Процессор";

    /**
     * Константа, хранящая название столбца c колличеством ядер процессора.
     */
    private static final String CORES_NUMBER = "Кол-во ядер";

    /**
     * Константа, хранящая название столбца c диагоналями экранов.
     */
    private static final String DIAGONAL = "Диагональ, \"";

    /**
     * Константа, хранящая название столбца c объемами жествого диска.
     */
    private static final String SSD_VOLUME = "Объем SSD, ГБ";

    /**
     * Константа, хранящая название столбца c объемами оперативной памяти.
     */
    private static final String RAM = "Объем RAM, ГБ";

    /**
     * Константа, хранящая название столбца с оперционными системами.
     */
    private static final String OPERATION_SYSTEM = "Операционная система";

    /**
     * Константа, хранящая название столбца с цветами корпусов.
     */
    private static final String COLOR = "Цвет";

    /**
     * Константа, хранящая название столбца с ценами.
     */
    private static final String PRICE = "Цена, руб.";

    /**
     * Константа, хранящая регулярное выражение для проверки числа.
     */
    private static final String NUMBER_REGEX = "^ *[0-9]+ *$";

    /**
     * Константа, хранящая регулярное выражение для проверки диапазона.
     */
    private static final String RANGE_REGEX = "^ *[0-9]+ *-+ *[0-9]+ *$";

    /**
     * Объект контроллера.
     */
    private final NotebookController notebookController;

    /**
     * Объект фильтра для поиска.
     */
    private final NotebookFilter notebookFilter;

    /**
     * Объект ридера для ввода данных в консоли.
     */
    private final BufferedReader reader;

    /**
     * Инициализация объекта представления.
     * @param notebookController Объект контроллера.
     * @param notebookFilter Объект фильтра для поиска.
     * @throws IllegalArgumentException Возбуждается, если передан неинициализированный объект.
     */
    public MainView(NotebookController notebookController, NotebookFilter notebookFilter)
            throws IllegalArgumentException {
        if (notebookController == null || notebookFilter == null)
            throw new IllegalArgumentException();

        this.notebookController = notebookController;
        this.notebookFilter = notebookFilter;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод начала работы с представлением.
     */
    public void Start() throws IOException {
        while (true) {
            outputNotebooks();
            System.out.println();
            String[] menuItems = new String[] { "Настроить фильтры." };
            switch (selectMenuItem(menuItems, "Выход")) {
                case -1:
                    return;
                case 0:
                    settingsFilters();
            }
        }
    }

    /**
     * Метод настроек фильтра.
     */
    private void settingsFilters() throws IOException {
        while (true) {
            String[] menuItems = new String[] {
                    "Производитель" + (notebookFilter.getManufacturer() == null ? ""
                            : (" (" + notebookFilter.getManufacturer() + ')')),
                    "Год" + (notebookFilter.getYear() == null ? ""
                            : (" (" + notebookFilter.getYear().min() + " - " + notebookFilter.getYear().max() + ')')),
                    "Процессор" + (notebookFilter.getProcessor() == null ? ""
                            : (" (" + notebookFilter.getProcessor() + ')')),
                    "Кол-во ядер" + (notebookFilter.getCoresNumber() == null ? ""
                            : (" (" + notebookFilter.getCoresNumber().min() + " - " + notebookFilter.getCoresNumber().max() + ')')),
                    "Диагональ" + (notebookFilter.getDiagonal() == null ? ""
                            : (" (" + notebookFilter.getDiagonal().min() + " - " + notebookFilter.getDiagonal().max() + ')')),
                    "Объем SSD" + (notebookFilter.getSsdVolume() == null ? ""
                            : (" (" + notebookFilter.getSsdVolume().min() + " - " + notebookFilter.getSsdVolume().max() + ')')),
                    "Объем RAM" + (notebookFilter.getRam() == null ? ""
                            : (" (" + notebookFilter.getRam().min() + " - " + notebookFilter.getRam().max() + ')')),
                    "Операционная система" + (notebookFilter.getOperationSystem() == null ? ""
                            : (" (" + notebookFilter.getOperationSystem() + ')')),
                    "Цена" + (notebookFilter.getPrice() == null ? ""
                            : (" (" + notebookFilter.getPrice().min() + " - " + notebookFilter.getPrice().max() + ')')),
            };

            String title = "Введите слово или часть слова для поиска: ";
            String rangeTitle = "Укажите диапазон поиска в формате 'X' или 'X - X': ";

            switch (selectMenuItem(menuItems, "Назад")) {
                case -1:
                    return;
                case 0:
                    notebookFilter.setManufacturer(inputValueFilter(title));
                    break;
                case 1:
                    notebookFilter.setYear(inputRange(rangeTitle));
                    break;
                case 2:
                    notebookFilter.setProcessor(inputValueFilter(title));
                    break;
                case 3:
                    notebookFilter.setCoresNumber(inputRange(rangeTitle));
                    break;
                case 4:
                    notebookFilter.setDiagonal(inputRange(rangeTitle));
                    break;
                case 5:
                    notebookFilter.setSsdVolume(inputRange(rangeTitle));
                    break;
                case 6:
                    notebookFilter.setRam(inputRange(rangeTitle));
                    break;
                case 7:
                    notebookFilter.setOperationSystem(inputValueFilter(title));
                    break;
                case 8:
                    notebookFilter.setPrice(inputRange(rangeTitle));
                    break;
            }
        }
    }

    /**
     * Метод ввода строки в консоль.
     * @param title Описание.
     * @return Введенная строка.
     */
    private String inputString(String title) throws IOException {
        System.out.print(title);
        return reader.readLine().trim();
    }

    /**
     * Метод ввода числа в консоль.
     * @param title Описание.
     * @return Введенное число.
     */
    private int inputNumber(String title) throws IOException {
        while (true) {
            System.out.print(title);
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Повторите попытку...");
            }
        }
    }

    /**
     * Метод ввода числа в консоль в заданном диапазоне.
     * @param title Описание.
     * @param range Диапазон для ввода.
     * @return Введенное число.
     */
    private int inputNumberInRange(String title, Range range) throws IOException {
        while (true) {
            int number = inputNumber(title);
            if (number >= range.min() && number <= range.max())
                return number;
            System.out.println("Нарушены границы диапазона! Повторите попытку...");
        }
    }

    /**
     * Метод ввода диапазона чисел в консоль.
     * @param title Описание.
     * @return Введенный диапазон.
     */
    private Range inputRange(String title) throws IOException {
        while (true) {
            String input = inputString(title);
            if (input.isEmpty())
                return null;
            else if (Pattern.compile(NUMBER_REGEX).matcher(input).find()) {
                int num = Integer.parseInt(input);
                return new Range(num, num);
            } else if (Pattern.compile(RANGE_REGEX).matcher(input).find()) {
                String[] range = input.replace(" ", "").split("-");
                int min = Integer.parseInt(range[0]);
                int max = Integer.parseInt(range[1]);
                if (max < min) {
                    System.out.println("Некорректный ввод! Повторите попытку...");
                    continue;
                }
                return new Range(min, max);
            }
            System.out.println("Некорректный ввод! Повторите попытку...");
        }
    }

    /**
     * Метод ввода значения поля фильтра в консоль.
     * @param title Описание.
     * @return Введенное значение.
     */
    private String inputValueFilter(String title) throws IOException {
        String input = inputString(title);
        return input.isEmpty() ? null : input;
    }

    /**
     * Метод выбора пункта меню.
     * @param menuItems Пункты меню.
     * @param backName Имя пункта выхода из меню.
     * @return Индекс введенного пункта меню.
     */
    private int selectMenuItem(String[] menuItems, String backName) throws IOException {
        for (int i = 0; i < menuItems.length; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(menuItems[i]);
        }
        System.out.print("0. ");
        System.out.println(backName);
        System.out.println();

        return inputNumberInRange("Укажите пункт меню: ", new Range(0, menuItems.length)) - 1;
    }

    /**
     * Вывод списка ноутбуков в консоль в виде таблицы.
     */
    private void outputNotebooks() {
        Set<Notebook> notebooks = notebookController.getNotebooks(notebookFilter);
        int numberMax = Integer.toString(notebooks.size()).length() + 1;
        int manufacturerMax = MANUFACTURER.length();
        int yearMax = YEAR.length();
        int processorMax = PROCESSOR.length();
        int coresNumberMax = CORES_NUMBER.length();
        int diagonalMax = DIAGONAL.length();
        int ssdVolumeMax = SSD_VOLUME.length();
        int ramMax = RAM.length();
        int operationSystemMax = OPERATION_SYSTEM.length();
        int colorMax = COLOR.length();
        int priceMax = PRICE.length();

        for (Notebook notebook : notebooks) {
            manufacturerMax = Math.max(manufacturerMax, notebook.getManufacturer().length());
            yearMax = Math.max(yearMax, Integer.toString(notebook.getYear()).length());
            processorMax = Math.max(processorMax, notebook.getProcessor().length());
            coresNumberMax = Math.max(coresNumberMax, Integer.toString(notebook.getCoresNumber()).length());
            diagonalMax = Math.max(diagonalMax, Integer.toString(notebook.getDiagonal()).length());
            ssdVolumeMax = Math.max(ssdVolumeMax, Integer.toString(notebook.getSsdVolume()).length());
            ramMax = Math.max(ramMax, Integer.toString(notebook.getRam()).length());
            operationSystemMax = Math.max(operationSystemMax, notebook.getOperationSystem().length());
            colorMax = Math.max(colorMax, notebook.getColor().length());
            priceMax = Math.max(priceMax, Integer.toString(notebook.getPrice()).length());
        }

        System.out.println();
        outputWithSpaces("#", numberMax);
        outputWithSpaces(MANUFACTURER, manufacturerMax);
        outputWithSpaces(YEAR, yearMax);
        outputWithSpaces(PROCESSOR, processorMax);
        outputWithSpaces(CORES_NUMBER, coresNumberMax);
        outputWithSpaces(DIAGONAL, diagonalMax);
        outputWithSpaces(SSD_VOLUME, ssdVolumeMax);
        outputWithSpaces(RAM, ramMax);
        outputWithSpaces(OPERATION_SYSTEM, operationSystemMax);
        outputWithSpaces(COLOR, colorMax);
        outputWithSpaces(PRICE, priceMax);
        System.out.println('\n');

        int i = 1;
        for (Notebook notebook : notebooks) {
            outputWithSpaces(Integer.toString(i++), numberMax);
            outputWithSpaces(notebook.getManufacturer(), manufacturerMax);
            outputWithSpaces(Integer.toString(notebook.getYear()), yearMax);
            outputWithSpaces(notebook.getProcessor(), processorMax);
            outputWithSpaces(Integer.toString(notebook.getCoresNumber()), coresNumberMax);
            outputWithSpaces(Integer.toString(notebook.getDiagonal()), diagonalMax);
            outputWithSpaces(Integer.toString(notebook.getSsdVolume()), ssdVolumeMax);
            outputWithSpaces(Integer.toString(notebook.getRam()), ramMax);
            outputWithSpaces(notebook.getOperationSystem(), operationSystemMax);
            outputWithSpaces(notebook.getColor(), colorMax);
            outputWithSpaces(Integer.toString(notebook.getPrice()), priceMax);
            System.out.println();
        }
    }

    /**
     * Метод вывода значения в таблицу ноутбуков.
     * @param value Выводимое значение.
     * @param length Ширина столбца.
     */
    private void outputWithSpaces(String value, int length) {
        for(int i = 0; i <= length - value.length(); i++)
            System.out.print(' ');
        System.out.print(value);
        System.out.print("  ");
    }
}
