import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws IOException {
        task1();
    }

    //region Задача 1

    /**
     * Условие:
     * В консоли запросить имя пользователя.
     * В зависимости от текущего времени, вывести приветствие вида
     * • "Доброе утро, Имя!", если время от 05:00 до 11:59;
     * • "Добрый день, Имя!", если время от 12:00 до 17:59;
     * • "Добрый вечер, Имя!", если время от 18:00 до 22:59;
     * • "Доброй ночи, Имя!", если время от 23:00 до 4:59
     */
    private static void task1() throws IOException {
        System.out.println("\n ** Вывод приветствия в зависимости от времени суток. ** \n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        while (Objects.equals(name, ""))
        {
            System.out.print("Укажите Ваше имя: ");
            name = reader.readLine().trim();
            if (Objects.equals(name, ""))
                System.out.println("Имя не должно быть пустым! Повторите попытку...");
        }
        DateFormat dateFormat = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(dateFormat.format(new Date()));
        String timeOfDay;
        if (hour >= 5 && hour < 12)
            timeOfDay = "Доброе утро";
        else if (hour >= 12 && hour < 18)
            timeOfDay = "Добрый день";
        else if (hour >= 18 && hour < 23)
            timeOfDay = "Добрый день";
        else
            timeOfDay = "Доброй ночи";

        String output = String.format("%s, %s!", timeOfDay, name);
        System.out.println(output);
    }

    //endregion
}
