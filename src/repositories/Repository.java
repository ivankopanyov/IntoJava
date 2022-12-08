package repositories;

import model.Model;
import repositories.exceptions.RepositoryException;

import java.util.Collection;

/**
 * Репозиторий.
 */
public interface Repository {

    /**
     * Добавление объекта модели в репозиторий.
     * @param model Экземпляр модели.
     * @throws RepositoryException Не удалось добавить объект в репозиторий.
     */
    void add(Model model) throws RepositoryException;

    /**
     * Метод получения коллекции объектов модели из репозитория по ключу.
     * @param key Ключ.
     * @return Коллекция объектов модели.
     */
    Collection<Model> get(String key);

    /**
     * Метод получения коллекции всех объектов модели из репозитория.
     * @return Коллекция объектов модели.
     */
    Collection<Model> getAll();
}
