package Controllers;

import Models.Notebook;
import Models.NotebookFilter;
import Models.Range;
import Repositories.NotebookRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, описывающий контроллер для работы с объектами ноутбуков.
 */
public class NotebookController {

    /**
     * Объект репозитория, хранящий экземпляры ноутбуков.
     */
    private final NotebookRepository repository;

    /**
     * Инициализация объекта контроллера.
     * @param repository Объект репозитория, хранящий экземпляры ноутбуков.
     * @throws IllegalArgumentException Возбуждается, если передан неинициализированный репозиторий.
     */
    public NotebookController(NotebookRepository repository) throws IllegalArgumentException {

        if (repository == null)
            throw new IllegalArgumentException();

        this.repository = repository;
    }

    /**
     * Метод добавления ноутбука в репозиторий.
     * @param notebook Экземпляр ноутбука для добавления.
     */
    public void add(Notebook notebook) {
        repository.add(notebook);
    }

    /**
     * Метод, возвращающий все экземпляры ноутбуков, хранящиеся в репозитории.
     * @return Экземпляры ноутбуков, хранящиеся в репозитории.
     */
    public Set<Notebook> getNotebooks() {
        return repository.getAll();
    }

    /**
     * Метод, возвращающий отфильтрованное множество экземпляров ноутбуков, хранящихся в репозитории.
     * @param notebookFilter Объект фильтра для поиска ноутбуков
     * @return Отфильтрованное множество экземпляров ноутбуков.
     */
    public Set<Notebook> getNotebooks(NotebookFilter notebookFilter) {
        if (notebookFilter == null)
            return getNotebooks();

        Set<Notebook> notebooks = repository.getAll();

        HashSet<Notebook> result = new HashSet<>();
        for (Notebook notebook: notebooks) {
            if (!checkRangeFilter(notebookFilter.getYear(), notebook.getYear())) continue;
            if (!checkRangeFilter(notebookFilter.getCoresNumber(), notebook.getCoresNumber())) continue;
            if (!checkRangeFilter(notebookFilter.getDiagonal(), notebook.getDiagonal())) continue;
            if (!checkRangeFilter(notebookFilter.getSsdVolume(), notebook.getSsdVolume())) continue;
            if (!checkRangeFilter(notebookFilter.getRam(), notebook.getRam())) continue;
            if (!checkRangeFilter(notebookFilter.getPrice(), notebook.getPrice())) continue;
            if (!checkStringFilter(notebookFilter.getManufacturer(), notebook.getManufacturer())) continue;
            if (!checkStringFilter(notebookFilter.getProcessor(), notebook.getProcessor())) continue;
            if (!checkStringFilter(notebookFilter.getOperationSystem(), notebook.getOperationSystem())) continue;
            if (!checkStringFilter(notebookFilter.getColor(), notebook.getColor())) continue;
            result.add(notebook);
        }
        return result;
    }

    /**
     * Метод проверки экземпляра ноутбука на валидность для переданного фильтра.
     * @param filterValue Значение поля фильтра.
     * @param notebookValue Значения поля экземпляра ноутбука.
     * @return Результат проверки.
     */
    private boolean checkStringFilter(String filterValue, String notebookValue) {
        if (filterValue == null)
            return true;

        filterValue = filterValue.trim().toLowerCase();
        if (filterValue.isEmpty())
            return true;

        return notebookValue.trim().toLowerCase().contains(filterValue);
    }

    /**
     * Метод проверки экземпляра ноутбука на валидность для переданного фильтра.
     * @param range Диапазон поиска.
     * @param notebookValue Значения поля экземпляра ноутбука.
     * @return Результат проверки.
     */
    private boolean checkRangeFilter(Range range, int notebookValue) {
        if (range == null)
            return true;

        return notebookValue >= range.min() && notebookValue <= range.max();
    }
}
