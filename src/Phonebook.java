import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {

    int counter = 0;

    private final Map<Integer, String> persons;

    private final Map<String, Integer> phones;

    public Phonebook() {
        persons = new HashMap<>();
        phones = new HashMap<>();
    }

    public int addPhone(String name, String phone) {
        persons.put(++counter, name);
        removePhone(phone);
        phones.put(phone, counter);
        return counter;
    }

    public void addPhone(int personId, String phone) {
        if (!persons.containsKey(personId))
            return;
        removePhone(phone);
        phones.put(phone, personId);
    }

    public void removePhone(String phone) {
        if (!phones.containsKey(phone))
            return;

        int id = phones.get(phone);
        phones.remove(phone);

        if (!phones.containsValue(id))
            persons.remove(id);
    }

    public ArrayList<String> getPhones(int personId) {
        ArrayList<String> result = new ArrayList<>();

        if (!persons.containsKey(personId))
            return result;

        for (String phone: phones.keySet())
            if (phones.get(phone) == personId)
                result.add(phone);

        return result;
    }

    public HashMap<Integer, String> getPersons() {
        return new HashMap<Integer, String>(persons);
    }
}
