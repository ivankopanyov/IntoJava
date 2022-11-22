package infrastructure.application;

public class ServiceBuilder {

    private final AppBuilder appBuilder;

    ServiceBuilder(AppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addService(abstractClassService, implementClassService);
        return this;
    }

    public <TAbstract, TImplement extends TAbstract> ServiceBuilder addSingletonService(
            Class<TAbstract> abstractClassService,
            Class<TImplement> implementClassService) {

        appBuilder.addSingletonService(abstractClassService, implementClassService);
        return this;
    }

    public Application build() {
        return appBuilder.build();
    }
}
