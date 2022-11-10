/**
 * Рекорд, описывающий адрес.
 * @param street Улица.
 * @param number Номер дома.
 */
public record Address(String street, String number) {

    /**
     * Переопределение метода преобразования объекта адреса в строку.
     * @return Строка с улицей и номером дома.
     */
    @Override
    public String toString() {
        return street + ", №" + number;
    }

    /**
     * Переопределение метода сравнения экземпляров адресов.
     * @param obj Экземпляр адреса для сравнения.
     * @return Результат сравнения.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != Address.class)
            return false;

        Address address = (Address) obj;

        return street.equals(address.street) && number.equals(address.number);
    }

    /**
     * Переопределение метода получения хэш-кода экземпляра адреса.
     * @return Хэш-код адреса.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + street.hashCode();
        result = result * 31 + number.hashCode();
        return result;
    }
}
