import java.util.*;

/**
 * Класс, описывающий коллекцию мест города.
 */
public class CityMap implements Iterable<Place> {

    /**
     * Экземпляр мэпа для хранения мест.
     */
    private final Map<Address, Set<Place>> places;

    /**
     * Инициализация коллекции мест города.
     */
    public CityMap() {
        places = new HashMap<>();
    }

    /**
     * Метод добавления экземпляра места в коллекцию.
     * @param place Экземпляр места для добавления.
     */
    public void add(Place place) {
        if (place == null || place.address() == null)
            return;

        if (!places.containsKey(place.address())) {
            HashSet<Place> set = new HashSet<>();
            set.add(place);
            places.put(place.address(), set);
        } else
            places.get(place.address()).add(place);
    }

    /**
     * Метод получения массива мест по переданному адресу.
     * @param address Адрес для поиска мест.
     * @return Массив, содержащий места с переданным адресом.
     */
    public Place[] get(Address address) {
        if (address == null || !places.containsKey(address))
            return null;

        Set<Place> set = places.get(address);
        Place[] array = new Place[set.size()];
        int i = 0;
        for (Place place: set)
            array[i++] = place;

        return array;
    }

    /**
     * Метод получения массива адресов всех мест, содержащихся в коллекции.
     * @return Массив адресов.
     */
    public Address[] get() {
        Set<Address> set = places.keySet();
        Address[] array = new Address[set.size()];
        int i = 0;
        for (Address address: set)
            array[i++] = address;

        return array;
    }

    /**
     * Метод удаления экземпляра места из коллекции.
     * @param place Экземпляр места для удаления.
     */
    public void remove(Place place) {
        if (place == null || place.address() == null || !this.places.containsKey(place.address()))
            return;

        Set<Place> set = this.places.get(place.address());

        if (set.contains(place)) {
            set.remove(place);
            if (set.size() == 0)
                this.places.remove(place.address());
        }
    }

    /**
     * Переопределение метода, возвращающего итератор коллекции.
     * @return Итератор коллекции.
     */
    @Override
    public Iterator<Place> iterator() {
        return new CityMapIterator();
    }

    /**
     * Класс, описывающий итератор для коллекции мест города.
     */
    private class CityMapIterator implements Iterator<Place> {

        /**
         * Массив адресов всех мест, хранящихся в коллекции.
         */
        private final Address[] addressesArray;

        /**
         * Массив мест с текущим адресом.
         */
        private Place[] placesArray;

        /**
         * Указатель на текущие индекс адреса.
         */
        private int addressPointer;

        /**
         * Указатель на текущий индекс места.
         */
        private int placePointer;

        /**
         * Инициализация экземпляра итератора.
         */
        public CityMapIterator() {
            addressesArray = get();
            placesArray = null;
            addressPointer = 0;
            placePointer = 0;
        }

        /**
         * Переопределение метода проверки наличия следущего места.
         * @return Результат проверки.
         */
        @Override
        public boolean hasNext() {
            if (addressPointer == addressesArray.length)
                return false;

            if (placesArray == null)
                placesArray = get(addressesArray[0]);
            else if (placePointer == placesArray.length) {
                if (++addressPointer == addressesArray.length)
                    return false;
                placesArray = get(addressesArray[addressPointer]);
                placePointer = 0;
            }

            return true;
        }

        /**
         * Переопределение метода, возвращающего следущее место.
         * @return Следущий экземпляр места.
         */
        @Override
        public Place next() {
            return placesArray[placePointer++];
        }
    }
}
