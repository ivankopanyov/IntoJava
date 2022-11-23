package presenter;

import infrastructure.application.PresenterBase;
import infrastructure.application.PresenterHandler;
import view.ExitView;

/**
 * Класс, описывающий презентер выхода из приложения.
 */
public class ExitPresenter extends PresenterBase {

    /**
     * Объект представления.
     */
    private final ExitView view;

    /**
     * Инициализация объекта презентера.
     * @param presenterHandler Обработчик презентеров.
     * @param view Объект предствления.
     */
    public ExitPresenter(PresenterHandler presenterHandler, ExitView view) {
        super(presenterHandler);
        this.view = view;
    }

    /**
     * Метод запуска презентера.
     */
    @Override
    public void run() {
        view.print("Завершение работы приложения...");
    }
}
