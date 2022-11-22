package infrastructure.application;

public class ViewBuilder {

    private final AppBuilder appBuilder;

    ViewBuilder(AppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    public ExtraViewBuilder addView(Class<? extends ViewBase> viewClass) {
        appBuilder.addView(viewClass);
        return new ExtraViewBuilder(appBuilder);
    }
}
