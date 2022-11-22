package infrastructure.application;

public class PresenterBuilder {

    private final AppBuilder appBuilder;

    PresenterBuilder(AppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    public PresenterBuilder addPresenter(Class<? extends PresenterBase> presenterClass) {
        appBuilder.addPresenter(presenterClass);
        return this;
    }

    public ViewBuilder addView(Class<? extends ViewBase> viewClass) {
        appBuilder.addView(viewClass);
        return new ViewBuilder(appBuilder);
    }
}
