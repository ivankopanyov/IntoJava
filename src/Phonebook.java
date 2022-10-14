import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, описывающий телефонную книгу.
 */
public class Phonebook {

    /**
     * Счетсик для присвоения идентификаторов новым контактам.
     */
    int counter = 0;

    /**
     * Мэп, хранящий идентификаторы и имена контактов.
     */
    private final Map<Integer, String> persons;

    /**
     * Мэп, хранящий номера телефонов и идентификаторы контактов.
     */
    private final Map<String, Integer> phones;

    /**
     * Инициализация объекта телефонной книги.
     */
    public Phonebook() {
        persons = new HashMap<>();
        phones = new HashMap<>();
    }

    /**
     * Метод добавления номера телефона для нового контакта.
     * @param name Имя нового контатка.
     * @param phone Номер телефона нового контакта.
     * @return Идентификатор нового контакта.
     */
    public int addPhone(String name, String phone) {
        persons.put(++counter, name);
        removePhone(phone);
        phones.put(phone, counter);
        return counter;
    }

    /**
     * Метод добавления номера телефона существующему контатку.
     * @param personId Идентификатор контакта.
     * @param phone Номер телефона.
     */
    public void addPhone(int personId, String phone) {
        if (!persons.containsKey(personId))
            return;
        removePhone(phone);
        phones.put(phone, personId);
    }

    /**
     * Метод удаления номера телефона.
     * @param phone Номер телефона для удаления.
     */
    public void removePhone(String phone) {
        if (!phones.containsKey(phone))
            return;

        int id = phones.get(phone);
        phones.remove(phone);

        if (!phones.containsValue(id))
            persons.remove(id);
    }

    /**
     * Метод получения списка номеров телефонов контакта.
     * @param personId Идентификатор контаткта.
     * @return Список номеров контакта.
     */
    public ArrayList<String> getPhones(int personId) {
        if (!persons.containsKey(personId))
            return null;

        ArrayList<String> result = new ArrayList<>();

        for (String phone: phones.keySet())
            if (phones.get(phone) == personId)
                result.add(phone);

        return result;
    }

    /**
     * Метод получения мэпа контактов телефонной книги.
     * @return Копия мэпа контактов.
     */
    public HashMap<Integer, String> getPersons() {
        return new HashMap<Integer, String>(persons);
    }
}
