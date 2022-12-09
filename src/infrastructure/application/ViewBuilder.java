package infrastructure.application;

import infrastructure.application.exceptions.ApplicationBuilderException;

/**
 * Вспомогательный класс, описывающий билдер по добавлению классов представлений.
 */
public class ViewBuilder {

    /**
     * Ссылка на главный билдер.
     */
    private final ApplicationBuilder appBuilder;

    /**
     * Инициализация билдера.
     * @param appBuilder Главный билдер.
     */
    ViewBuilder(ApplicationBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    /**
     * Метод добавления класса представления.
     * @param viewClass Класс представления
     * @return Объект текущего билдера.
     */
    public ViewBuilder addView(Class<? extends ViewBase> viewClass) {
        appBuilder.addView(viewClass);
        return this;
    }

    /**
     * Метод добавления класса сервиса.
     * @param abstractClassService Абстрактный класс.
     * @param implementClassService Имплементирующий класс.
     * @return Объект сервис билдера.
     * @param <TAbstract> Тип абстрактного класса.
     * @param <TImplement> Тип имплементирующего класса.
     */
    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addService(abstractClassService, implementClassService);
        return new ServiceBuilder(appBuilder);
    }

    /**
     * Метод добавления класса синглтон сервиса.
     * @param abstractClassService Абстрактный класс.
     * @param implementClassService Имплементирующий класс.
     * @return Объект сервис билдера.
     * @param <TAbstract> Тип абстрактного класса.
     * @param <TImplement> Тип имплементирующего класса.
     */
    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addSingletonService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addSingletonService(abstractClassService, implementClassService);
        return new ServiceBuilder(appBuilder);
    }

    /**
     * Метод запуска сборки приложения.
     * @return Объект приложения.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    public Application build() throws ApplicationBuilderException {
        return appBuilder.build();
    }
}
