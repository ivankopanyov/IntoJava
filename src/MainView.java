import GenealogicalTree.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Класс, описывающий главное представление.
 */
public class MainView {

    /**
     * Объект генеалогического дерева.
     */
    private GenealogicalTree tree;

    /**
     * Объект ридера для ввода данных в консоли.
     */
    private final BufferedReader reader;

    /**
     * Инициализация объекта главного представления.
     */
    public MainView() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод запуска главного представления.
     */
    public void Start() throws IOException {
        System.out.println(" ** Генеалогическое дерево. ** ");
        while (true) {
            System.out.println();
            String[] menuItems = new String[] { "Создать генеалогическое дерево.", "Сгенерировать случайное генеалогическое дерево" };
            switch (selectMenuItem(menuItems, "Выход")) {
                case -1:
                    return;
                case 0:
                    createTree();
                    break;
                case 1:
                    generateTree();
                    break;
            }
        }
    }

    /**
     * Метод создания генеалогического дерева.
     */
    private void createTree() throws IOException {
        Object[] person = addPerson("Укажите имя и фамилию самого дальнего предка через пробел: ");
        if (person == null)
            return;
        tree = new GenealogicalTree((String) person[0], (String) person[1], (Gender) person[2]);
        System.out.println();
        System.out.println(tree);
        mainMenu();
    }

    /**
     * Метод генерации генеалогического дерева.
     */
    private void generateTree() throws IOException {
        int generations = inputNumberInRange("Укажите колличество поколений(1 - 10): ", 1, 10);
        int min = inputNumberInRange("Укажите минимальное колличество детей в паре(0 - 10): ", 0, 10);
        int max = min;
        if (max < 10)
            max = inputNumberInRange("Укажите минимальное колличество детей в паре(" + min + " - 10): ", min, 10);
        tree = GenealogicalTreeBuilder.generateGeneologicalTree(min, max, generations);
        System.out.println();
        System.out.println(tree);
        mainMenu();
    }

    /**
     * Метод отображения главного меню.
     */
    private void mainMenu() throws IOException {
        while (true) {
            System.out.println();
            String[] menuItems = new String[] { "Добавить", "Посмотреть" };
            switch (selectMenuItem(menuItems, "Выход")) {
                case -1:
                    return;
                case 0:
                    addPerson();
                    break;
                case 1:
                    System.out.println();
                    System.out.println(tree);
                    break;
            }
        }
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     */
    private void addPerson() throws IOException {
        System.out.println();
        Object[] person = addPerson("Укажите имя и фамилию через пробел: ");
        if (person == null)
            return;

        Person parent;
        while (true) {
            parent = tree.getPerson(inputNumber("Укажите ID отца или матери: "));
            if (parent == null)
                System.out.println("Человек с таким ID не найден. Повторите попытку...");
            else
                break;
        }

        while (true) {
            String title = "Укажите ID " + (parent.getGender() == Gender.male ? "матери" : "отца") + " или имя и фамилию через пробел: ";
            String input = inputString(title);

            try {
                Person secondParent = tree.getPerson(Integer.parseInt(input));
                Person newPerson = tree.addPerson(parent, secondParent, (String) person[0], (String) person[1], (Gender) person[2]);
                if (newPerson == null) {
                    System.out.println("Невозможно добавить " + (parent.getGender() == Gender.male ? "мать" : "отца") + " с указанным ID!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                String[] inputArray = input.split(" ");
                String name = inputArray[0];
                String surname = inputArray.length == 1 ? ""
                        : String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                tree.addPerson(parent, name, surname, (String) person[0], (String) person[1], (Gender) person[2]);
                break;
            }
        }

        System.out.println();
        System.out.println(tree);
    }


    /**
     * Метод добавления человека в генеалогическое дерево.
     */
    private Object[] addPerson(String title) throws IOException {
        String[] input = inputString(title).split(" ");
        String name = input[0];
        String surname = input.length == 1 ? ""
                : String.join(" ", Arrays.copyOfRange(input, 1, input.length));
        System.out.println("\nУкажите пол: ");
        String[] menuItems = new String[] { "Мужской", "Женский" };
        Gender gender = Gender.female;
        switch (selectMenuItem(menuItems, "Назад")) {
            case -1:
                return null;
            case 0:
                gender = Gender.male;
                break;
        }
        return new Object[] { name, surname, gender };
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
     * @param min Минимальное значение.
     * @param max Максимальное значение.
     * @return Введенное число.
     */
    private int inputNumberInRange(String title, int min, int max) throws IOException {
        while (true) {
            int number = inputNumber(title);
            if (number >= min && number <= max)
                return number;
            System.out.println("Нарушены границы диапазона! Повторите попытку...");
        }
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

        return inputNumberInRange("Укажите пункт меню: ", 0, menuItems.length) - 1;
    }
}
