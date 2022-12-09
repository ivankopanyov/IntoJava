package infrastructure.application;

import infrastructure.application.exceptions.ApplicationBuilderException;

/**
 * Вспомогательный класс, описывающий билдер для добавления классов презентеров.
 */
public class PresenterBuilder {

    /**
     * Ссылка на главный билдер.
     */
    private final ApplicationBuilder appBuilder;

    /**
     * Инициализация билдера.
     * @param appBuilder Главный билдер.
     */
    PresenterBuilder(ApplicationBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    /**
     * Метод добавления класса презентера.
     * @param key Ключ презентера.
     * @param presenterClass Класс презентера.
     * @return Объект текущего билдера.
     * @throws ApplicationBuilderException Возбуждается, если переданный объект не инициализирован.
     */
    public PresenterBuilder addPresenter(String key, Class<? extends PresenterBase> presenterClass) throws ApplicationBuilderException {
        appBuilder.addPresenter(key, presenterClass);
        return this;
    }

    /**
     * Метод добавления класса представления.
     * @param viewClass Класс представления.
     * @return Вспомпгптельный билдер для добавления классов представлений.
     */
    public ViewBuilder addView(Class<? extends ViewBase> viewClass) {
        appBuilder.addView(viewClass);
        return new ViewBuilder(appBuilder);
    }
}
