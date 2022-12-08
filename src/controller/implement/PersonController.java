package controller.implement;

import controller.Controller;
import model.Model;
import repositories.Repository;
import repositories.exceptions.RepositoryException;
import services.Mapper;
import services.exceptions.MapperException;
import view.View;

import java.util.Collection;

/**
 * Класс, описывающий контроллер для работы с объектами модели человека.
 */
public class PersonController implements Controller {

    /**
     * Объект представления.
     */
    private final View view;

    /**
     * Объект репозитория.
     */
    private final Repository repository;

    /**
     * Маппер.
     */
    private final Mapper mapper;

    /**
     * Инициализация объекта контроллера.
     * @param view Объект представления.
     * @param repository Объект репозитория.
     * @param mapper Маппер.
     */
    public PersonController(View view, Repository repository, Mapper mapper) {
        this.view = view;
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Метод запуска контроллера.
     */
    @Override
    public void start() {
        String[] items = new String[] {
                "Добавить запись",
                "Найти запись",
                "Все записи",
                "Выход"
        };

        while (true) {
            switch (view.select("Укажите номер пункта меню: ", items)) {
                case 0 -> create();
                case 1 -> printModels(repository.get(view.input("Введите фамилию: ")));
                case 2 -> printModels(repository.getAll());
                case 3 -> {
                    view.print("Завершение работы приложения...");
                    return;
                }
            }
        }
    }

    /**
     * Метод создания объекта модели.
     */
    public void create() {
        String value = view.input("Введите данные: ");
        try {
            Model model = mapper.map(value);
            repository.add(model);
        } catch (MapperException | RepositoryException e) {
            view.print(e.getMessage());
            return;
        }

        view.print("Данные успешно записаны.");
    }

    /**
     * Метод вывода объектов модели.
     * @param models Коллекция объектов модели.
     */
    public void printModels(Collection<Model> models) {
        if (models.size() == 0) {
            view.print("Записей не найдено!");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Model model: models)
            stringBuilder
                    .append(model.toString())
                    .append('\n');

        view.print(stringBuilder.toString());
    }
}
