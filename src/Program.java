import java.util.HashMap;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        task1();
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

    // Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова, Иван Юрин, Петр Лыков, Павел Чернов,
    // Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, Мария Рыкова, Марина Лугова, Анна Владимирова,
    // Иван Мечников, Петр Петин, Иван Ежов.

    /**
     * Задание:
     * Пусть дан список сотрудников.
     * Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
     * Отсортировать по убыванию популярности.
     */
    private static void task2() {

    }

    //endregion

    //region Задание 3

    /**
     * Реализовать алгоритм пирамидальной сортировки (HeapSort)
     */
    private static void task3() {

    }

    //endregion
}
