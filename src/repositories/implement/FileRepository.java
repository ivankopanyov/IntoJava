package repositories.implement;

import model.Model;
import repositories.Repository;
import repositories.exceptions.RepositoryException;
import services.Mapper;
import services.exceptions.MapperException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, описывающий репозиторий, хранящий объекты в файлах.
 */
public class FileRepository implements Repository {

    /**
     * Директория для записи файлов.
     */
    private final String directoryName;

    /**
     * Экземпляр маппера.
     */
    private final Mapper mapper;

    /**
     * Инициализация объекта репозитория.
     * @param directoryName Директория для записи файлов.
     * @param mapper Экземпляр маппера.
     */
    public FileRepository(String directoryName, Mapper mapper) {
        this.directoryName = directoryName;
        this.mapper = mapper;
    }

    /**
     * Добавление объекта модели в репозиторий.
     * @param model Экземпляр модели.
     * @throws RepositoryException Не удалось добавить объект в репозиторий.
     */
    @Override
    public void add(Model model) throws RepositoryException {
        try {
            Files.createDirectories(Paths.get(directoryName));
        } catch (Exception e) {
            throw new RepositoryException("Не удалось записать данные в файл!", e);
        }

        File file = new File(directoryName + '/' + model.getKey().toLowerCase() + ".txt");
        try (FileWriter fileWriter = new FileWriter(file,true)) {
            fileWriter.write(model.toString() + '\n');
        } catch (IOException e) {
            throw new RepositoryException("Не удалось записать данные в файл!", e);
        }
    }

    /**
     * Метод получения коллекции объектов модели из репозитория по ключу.
     * @param key Ключ.
     * @return Коллекция объектов модели.
     */
    @Override
    public Collection<Model> get(String key) {
        try(BufferedReader br = new BufferedReader(new FileReader(directoryName + '/' + key.trim().toLowerCase() + ".txt"))) {
            Collection<Model> models = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                try {
                    models.add(mapper.map(line));
                } catch (MapperException ignored) { }
                line = br.readLine();
            }
            return models;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Метод получения коллекции всех объектов модели из репозитория.
     * @return Коллекция объектов модели.
     */
    @Override
    public Collection<Model> getAll() {
        try (Stream<Path> stream = Files.list(Paths.get(directoryName))) {
            Set<String> files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());

            Collection<Model> models = new ArrayList<>();
            for (String file: files) {
                String[] array = file.split("\\.");
                if (array.length <= 1)
                    continue;

                models.addAll(get(String.join(".", Arrays.copyOf(array, array.length - 1))));
            }
            return models;

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
