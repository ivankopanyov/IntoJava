import GenealogicalTree.Gender;
import GenealogicalTree.GenealogicalTree;
import GenealogicalTree.GenealogicalTreeBuilder;
import GenealogicalTree.Person;

import java.io.IOException;

public class Program {

    /*
    Реализовать, с учетом ооп подхода, приложение
    Для проведения исследований с генеалогическим древом.
    Идея: описать некоторое количество компонент, например:
    модель человека
    компонента хранения связей и отношений между людьми: родитель, ребёнок - классика,
    но можно подумать и про отношение, брат, свекровь, сестра и т. д.
    компонент для проведения исследований
    дополнительные компоненты, например отвечающие за вывод данных в консоль, загрузку и сохранения в файл,
    получение\построение отдельных моделей человека
    Под “проведением исследования” можно понимать получение всех детей выбранного человека.
     */

    /**
     * Точка входа в приложение;
     */
    public static void main(String[] args) throws IOException {
        MainView view = new MainView();
        view.Start();
    }
}