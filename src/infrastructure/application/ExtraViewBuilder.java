package infrastructure.application;

public class ExtraViewBuilder {

    private final AppBuilder appBuilder;

    ExtraViewBuilder(AppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    public ExtraViewBuilder addView(Class<? extends ViewBase> viewClass) {
        appBuilder.addView(viewClass);
        return this;
    }

    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addService(abstractClassService, implementClassService);
        return new ServiceBuilder(appBuilder);
    }

    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addSingletonService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addSingletonService(abstractClassService, implementClassService);
        return new ServiceBuilder(appBuilder);
    }

    public Application build() {
        return appBuilder.build();
    }
}
