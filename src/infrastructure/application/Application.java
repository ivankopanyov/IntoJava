package infrastructure.application;

import infrastructure.application.exceptions.PresenterHandlerException;

/**
 * Класс, описывающий приложение.
 */
public class Application {

    /**
     * Объект обработчика презентеров.
     */
    private final PresenterHandler presenterHandler;

    /**
     * Инициализация объекта приложения.
     * @param presenterHandler Объект обработчика презентеров.
     */
    Application(PresenterHandler presenterHandler) {
        this.presenterHandler = presenterHandler;
    }

    /**
     * Метод запуска приложения.
     * @throws PresenterHandlerException Возбуждается, если передан несуществующий ключ презентера.
     */
    public void run() throws PresenterHandlerException {
        presenterHandler.start();
    }
}
