package Models;

/**
 * Диапазон значений целых чисел.
 * @param min Минимальное значение.
 * @param max Максимальное значение.
 */
public record Range(int min, int max) {

    /**
     * Инициализация объекта диапазон значений целых чисел.
     * @param min Минимальное значение.
     * @param max Максимальное значение.
     */
    public Range {
        if (max < min)
            throw new IllegalArgumentException("Минимальное значение диапазона не должно превышать максимальное.");
    }
}
