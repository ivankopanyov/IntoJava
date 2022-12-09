package infrastructure.application.linq;

/**
 * Linq контейнер.
 * @param <TStruct> Тип структуры данных.
 * @param <TItem> Тип элемента структуры данных.
 */
public interface ObjectLinq<TStruct, TItem> {

    /**
     * Метод возвращающий объект структуры данных, хранящейся в контейнере.
     * @return Объект структуры данных, хранящейся в контейнере.
     */
    TStruct get();

    /**
     * Метод сортировки.
     * @param predicate Условие.
     * @return Контейнер с результатом сортировки.
     */
    ObjectLinq<TStruct, TItem> orderBy(Expression<TItem, Integer> predicate);

    /**
     * Метод поиска первого вхождения, удовлетворяющего условию.
     * @param predicate Условие.
     * @return Результат поиска.
     */
    TItem first(Expression<TItem, Boolean> predicate);

    /**
     * Метод поиска всех вхождений, удовлетворяющих условию.
     * @param predicate Условие.
     * @return Результат поиска.
     */
    ObjectLinq<TStruct, TItem> where(Expression<TItem, Boolean> predicate);


    /**
     * Проверка наличия элемента в объекте структуры данных.
     * @param value Значение для проверки.
     * @return Результат проверки.
     */
    boolean contains(TItem value);

    /**
     * Метод, возвращающий колличество элементов в объекте структуры данных.
     * @return Колличество элементов в объекте структуры данных.
     */
    int count();
}
