package infrastructure.application;

import infrastructure.application.exceptions.PresenterHandlerException;

/**
 * Абстрактный класс, описывающий базовый презентер.
 */
public abstract class PresenterBase {

    /**
     * Объект обработчика презентеров.
     */
    private PresenterHandler presenterHandler;

    /**
     * Инициализация объекта презентера.
     * @param presenterHandler Обработчик презентеров.
     */
    public PresenterBase(PresenterHandler presenterHandler) {
        this.presenterHandler = presenterHandler;
    }

    /**
     * Метод, возвращающий объект обработчика презентеров.
     * @return Обработчик презентеров.
     */
    public PresenterHandler getPresenterHandler() {
        return presenterHandler;
    }

    /**
     * Метод изменения обработчика презентеров.
     * @param presenterHandler Объект обработчика презентеров.
     */
    void setPresenterHandler(PresenterHandler presenterHandler) {
        this.presenterHandler = presenterHandler;
    }

    /**
     * Стартовый метод презентера.
     * @throws PresenterHandlerException Возбуждается, если передан ключ, не содержащий объект презентера.
     */
    public abstract void run() throws PresenterHandlerException;
}
