import java.util.LinkedList;

/**
 * Класс, описывающий очередь.
 * @param <T> Тип элемента очереди.
 */
public class MyQueue<T> implements IQueue<T>{

    /**
     * Список, хранящий элементы очереди.
     */
    private LinkedList<T> list;

    /**
     * Инициализация объекта очереди.
     */
    public MyQueue() {
        list = new LinkedList<>();
    }

    /**
     * Метод, помещающий элемент в конец очереди.
     * @param value Значение элемента.
     */
    @Override
    public void enqueue(T value) {
        list.addLast(value);
    }

    /**
     * Метод, возвращающий первый элемент из очереди и удаляющий его.
     * @return Значение первого элемента
     * @throws IndexOutOfBoundsException Возбуждается, если очередь пуста.
     */
    @Override
    public T dequeue() {
        if (list.size() == 0)
            throw new IndexOutOfBoundsException();

        T value = list.getFirst();
        list.remove(0);
        return value;
    }

    /**
     * Метод, возвращающий первый элемент из очереди и удаляющий его.
     * @return Значение первого элемента
     * @throws IndexOutOfBoundsException Возбуждается, если очередь пуста.
     */
    @Override
    public T first() {
        if (list.size() == 0)
            throw new IndexOutOfBoundsException();

        return list.getFirst();
    }

    /**
     * Метод, возвращающий длину очереди.
     * @return Длина очереди.
     */
    @Override
    public int size() {
        return list.size();
    }
}
