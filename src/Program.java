import Controllers.NotebookController;
import Models.NotebookFilter;
import Repositories.NotebookRepository;
import Services.NotebookBuilder;
import Views.MainView;

import java.io.IOException;

public class Program {

    /*
        Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
        Создать множество ноутбуков.
        Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
        отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
        “Введите цифру, соответствующую необходимому критерию:
        1 - ОЗУ
        2 - Объем ЖД
        3 - Операционная система
        4 - Цвет …
        Далее нужно запросить минимальные значения для указанных критериев -
        сохранить параметры фильтрации можно также в Map.
        Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
     */

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws IOException {
        NotebookRepository repository = new NotebookRepository();
        NotebookFilter notebookFilter = new NotebookFilter();
        NotebookController notebookController = new NotebookController(repository);
        NotebookBuilder notebookBuilder = new NotebookBuilder();
        for (int i = 1; i <= 20; i++)
            notebookController.add(notebookBuilder.getRandom());
        MainView view = new MainView(notebookController, notebookFilter);
        view.Start();
    }
}
