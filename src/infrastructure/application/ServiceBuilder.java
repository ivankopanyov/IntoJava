package infrastructure.application;

import infrastructure.application.exceptions.ApplicationBuilderException;

/**
 * Вспомогательный класс, описывающий билдер по добавлению классов сервисов.
 */
public class ServiceBuilder {

    /**
     * Ссылка на главный билдер.
     */
    private final ApplicationBuilder appBuilder;

    /**
     * Инициализация билдера.
     * @param appBuilder Главный билдер.
     */
    ServiceBuilder(ApplicationBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    /**
     * Метод добавления класса сервиса.
     * @param abstractClassService Абстрактный класс.
     * @param implementClassService Имплементирующий класс.
     * @return Объект текущего билдера.
     * @param <TAbstract> Тип абстрактного класса.
     * @param <TImplement> Тип имплементирующего класса.
     */
    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addService(abstractClassService, implementClassService);
        return this;
    }

    /**
     * Метод добавления класса синглтон сервиса.
     * @param abstractClassService Абстрактный класс.
     * @param implementClassService Имплементирующий класс.
     * @return Объект текущего билдера.
     * @param <TAbstract> Тип абстрактного класса.
     * @param <TImplement> Тип имплементирующего класса.
     */
    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addSingletonService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addSingletonService(abstractClassService, implementClassService);
        return this;
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
