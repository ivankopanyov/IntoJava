/**
 * Если необходимо, исправьте данный код (задание 2
 * https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
 */
public class Task2 {

    /**
     * Точка входа в приложение.
     * Добавлена обработка исключений NullPointerException и IndexOutOfBoundsException.
     */
    public static void main(String[] args) {
        int[][] arr = new int[][] { null, new int[0], new int[9] };
        for (int[] intArray: arr) {

            try {
                int d = 0;
                double catchedRes1 = intArray[8] / d;
                System.out.println("catchedRes1 = " + catchedRes1);
            } catch (NullPointerException | ArithmeticException | IndexOutOfBoundsException e) {
                System.out.println("Catching exception: " + e);
            }

        }
    }
}
