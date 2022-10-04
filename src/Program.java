import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Program {
    public static void main(String[] args) {
        task2();
    }

    //region Общие методы

    /**
     * Метод, создающий объект логгера.
     *
     * @param fileLogsName Имя файла для записи логов.
     * @return Объект логгера.
     * @throws NullPointerException Возбуждается, если переданный параметр не инициализирован.
     * @throws IOException          Возбуждается, если передано некорректное имя файла,
     *                              или файл не доступен для записи.
     */
    private static Logger getFileSimpleLogger(String fileLogsName) throws NullPointerException, IOException {
        if (fileLogsName == null)
            throw new NullPointerException("Строка с именем файла не инициализирована.");

        Logger logger = Logger.getLogger(Program.class.getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(fileLogsName);
        } catch (IOException e) {
            throw new IOException("Невозможно создать файл для логирования.", e.getCause());
        }
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        return logger;
    }

    /**
     * Метод получения имен каталогов и файлов из переданного каталога.
     *
     * @param folderName Имя каталога, включающее путь у нему..
     * @return Массив с именами каталогов и файлов.
     * @throws NullPointerException  Возбуждается, если переданный параметр не инициализирован.
     * @throws FileNotFoundException Возбуждается, если переданный путь к директории не найден,
     *                               или передан путь к файлу.
     */
    private static String[] getFolderItemsNames(String folderName)
            throws NullPointerException, FileNotFoundException {
        if (folderName == null)
            throw new NullPointerException("Строка с именем каталога не инициализирована.");

        Path path = Paths.get(folderName);
        if (!Files.exists(path))
            throw new FileNotFoundException("Указанная директория не найдена.");

        File folder = new File(folderName);
        if (!folder.isDirectory())
            throw new FileNotFoundException("Передано имя файла, а не директории.");

        return folder.list();
    }

    //endregion

    //region Задача 1

    /**
     * Напишите метод, который вернет содержимое текущей папки в виде массива строк.
     * Напишите метод, который запишет массив, возвращенный предыдущим методом в файл.
     * Обработайте ошибки с помощью try-catch конструкции.
     * В случае возникновения исключения, оно должно записаться в лог-файл.
     */
    private static void task1() {
        Logger logger = null;
        try {
            logger = getFileSimpleLogger("log.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String folderName = null;
        String[] items = null;
        while (true) {
            System.out.print("Укажите путь к каталогу: ");
            try {
                folderName = reader.readLine();
                items = getFolderItemsNames(folderName);
                break;
            } catch (Exception e) {
                if (logger != null)
                    logger.log(Level.INFO, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }

        String tree = getTreeString(folderName, items);

        while (true) {
            System.out.print("Укажите путь к файлу для записи: ");
            try {
                String fileName = reader.readLine();
                writeToFile(fileName, tree);
                break;
            } catch (Exception e) {
                if (logger != null)
                    logger.log(Level.INFO, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Данные успешно записаны!");
    }

    /**
     * Метод формирования дерева каталогов и файлов.
     *
     * @param folderName Имя коневого каталога.
     * @param items      Массив имен подкаталогов и файлов.
     * @return Дерево каталогов и файлов.
     * @throws NullPointerException Возбуждается, если переданный параметр не инициализирован.
     */
    private static String getTreeString(String folderName, String[] items) throws NullPointerException {
        if (folderName == null || items == null)
            throw new NullPointerException("Параметр не инициализирован.");

        StringBuilder stringBuilder = new StringBuilder()
                .append(folderName)
                .append("┐\n");

        if (items.length == 0)
            return stringBuilder.toString();

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null)
                continue;

            stringBuilder
                    .append(" ".repeat(folderName.length()))
                    .append(i < items.length - 1 ? '├' : '└')
                    .append(items[i])
                    .append('\n');
        }

        return stringBuilder.toString();
    }

    /**
     * Метод записи данных в файл.
     *
     * @param fileName Имя файла для записи.
     * @param data     Данные для записи.
     * @throws NullPointerException Возбуждается, если переданный параметр не инициализирован.
     * @throws IOException          Возбуждается, если передано некорректное имя файла,
     *                              или файл не доступен для записи.
     */
    private static void writeToFile(String fileName, String data) throws NullPointerException, IOException {
        if (fileName == null || data == null)
            throw new NullPointerException("Параметр не инициализирован.");

        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new IOException("Не удалось записать данные в файл.", e.getCause());
        }
    }

    //endregion

    //region Задача 2

    /**
     * Напишите метод, который определит тип (расширение) файлов из текущей папки и выведет в консоль результат вида
     * 1 Расширение файла: txt
     * 2 Расширение файла: pdf
     * 3 Расширение файла:
     * 4 Расширение файла: jpg
     */
    private static void task2() {
        Logger logger = null;
        try {
            logger = getFileSimpleLogger("log.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String folderName = null;
        String[] items = null;
        while (true) {
            System.out.print("Укажите путь к каталогу: ");
            try {
                folderName = reader.readLine();
                items = getFolderItemsNames(folderName);
                break;
            } catch (Exception e) {
                if (logger != null)
                    logger.log(Level.INFO, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }

        if (items.length == 0) {
            System.out.print("Каталог пуст!");
            return;
        }

        for (int i = 0; i < items.length; i++) {
            String[] fileNameArray = items[i].split("\\.");
            String extension = fileNameArray.length == 1 ? "" : fileNameArray[fileNameArray.length - 1];
            System.out.print(i + 1);
            System.out.print(". Расширение файла: ");
            System.out.println(extension);
        }
    }

    //endregion
}