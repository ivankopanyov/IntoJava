package infrastructure.application;

import infrastructure.application.exceptions.PresenterHandlerException;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, описывающий обработчик презентеров в приложении.
 */
public class PresenterHandler {

    /**
     * Мэп, хранящий объекты презентеров.
     */
    private final Map<String, PresenterBase> presenters;

    /**
     * Ссылка на следущий презентер.
     */
    private PresenterBase nextPresenter;

    /**
     * Инициализация объекта обработчика презентеров.
     */
    PresenterHandler() {
        presenters = new HashMap<>();
    }

    /**
     * Метод добавления объекта презентера.
     * @param key Ключ презентера.
     * @param presenter Объект презентера.
     */
    void addPresenter(String key, PresenterBase presenter) {
        presenters.put(key, presenter);
    }

    /**
     * Метод запуска обработчика.
     * @throws PresenterHandlerException Возбуждаетя, если переданный ключ не содержит объектов презентера.
     */
    void start() throws PresenterHandlerException {
        while (nextPresenter != null) {
            PresenterBase temp = nextPresenter;
            nextPresenter = null;
            temp.run();
        }
    }

    /**
     * Проверка наличия объекта презентера по переданному ключу.
     * @param presenterKey Ключ презентера.
     * @return Результат проверки.
     */
    public boolean hasKey(String presenterKey) {
        return presenters.containsKey(presenterKey);
    }

    /**
     * Метод установки следущего презентера.
     * @param presenterKey Ключ следущего презентера.
     * @throws PresenterHandlerException Возбуждаетя, если переданный ключ не содержит объектов презентера.
     */
    public void setNextPresenter(String presenterKey) throws PresenterHandlerException {
        if (!presenters.containsKey(presenterKey))
            throw new PresenterHandlerException("Презентер с ключом " + presenterKey + " не зарегистрирован");

        nextPresenter = presenters.get(presenterKey);
    }

    /**
     * Установка стартового презентера.
     * @param presenterKey Ключ стартового презентера.
     */
    void setStartPresenter(String presenterKey) {
        nextPresenter = presenters.get(presenterKey);
    }
}
