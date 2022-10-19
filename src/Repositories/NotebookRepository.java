package Repositories;

import Models.Notebook;

import java.util.*;

/**
 * Класс, описывающий репозиторий для работы с объектами ноутбуков.
 */
public class NotebookRepository {

    /**
     * Объект множества для хранения объектов ноутбуков.
     */
    Set<Notebook> notebooks;

    /**
     * Инициализация объекта репозитория.
     */
    public NotebookRepository() {
        notebooks = new HashSet<>();
    }

    /**
     * Метод добавления объекта ноутбука в репозиторий.
     * @param notebook Объект ноутбука для добавления.
     */
    public void add(Notebook notebook) {
        if (notebook != null)
            notebooks.add(notebook);
    }

    /**
     * Метод, возвращающий множество всех объектов ноутбуков, хранящихся в репозитории.
     * @return Множество всех объектов ноутбуков, хранящихся в репозитории.
     */
    public Set<Notebook> getAll() {
        return new HashSet<>(notebooks);
    }
}
