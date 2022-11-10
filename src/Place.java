/**
 * Рекорд, описывающий место.
 * @param name Название места.
 * @param address Адрес места.
 */
public record Place(String name, Address address) {

    /**
     * Переопределение метода преобразования объекта места в строку.
     * @return Строка с названием места и адресом.
     */
    @Override
    public String toString() {
        return name + " (" + address + ")";
    }

    /**
     * Переопределение метода сравнения экземпляров мест.
     * @param obj Экземпляр места для сравнения.
     * @return Результат сравнения.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != Place.class)
            return false;

        Place place = (Place) obj;

        return name.equals(place.name) && address.equals(place.address);
    }

    /**
     * Переопределение метода получения хэш-кода экземпляра места.
     * @return Хэш-код места.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + address.hashCode();
        return result;
    }
}
