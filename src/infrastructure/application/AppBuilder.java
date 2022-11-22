package infrastructure.application;

import infrastructure.application.linq.Linq;

import java.lang.reflect.Constructor;
import java.util.*;

public class AppBuilder {

    private Class<? extends PresenterBase> presenterClass;

    private final Set<Class<? extends ViewBase>> viewsClasses;

    private final Map<Class<?>, Class<?>> services;

    private final Map<Class<?>, Class<?>> singletonServices;

    public AppBuilder() {
        this.viewsClasses = new HashSet<>();
        this.services = new HashMap<>();
        this.singletonServices = new HashMap<>();
    }

    public ViewBuilder addPresenter(Class<? extends PresenterBase> presenterClass) {
        this.presenterClass = presenterClass;
        return new ViewBuilder(this);
    }

    void addView(Class<? extends ViewBase> viewClass) {
        viewsClasses.add(viewClass);
    }

    void addService(Class<?> abstractClass, Class<?>implementClass) {
        singletonServices.remove(abstractClass);
        services.put(abstractClass, implementClass);
    }

    void addSingletonService(Class<?> abstractClass, Class<?>implementClass) {
        services.remove(abstractClass);
        singletonServices.put(abstractClass, implementClass);
    }

    Application build() {





        var presenterConstructors = presenterClass.getConstructors();

        if (presenterConstructors.length == 0)
            throw new IllegalArgumentException();

        Constructor<?> validConstructor = Linq.getObject(presenterConstructors)
                .orderBy(constructor -> constructor.getParameters().length)
                .first(constructor -> Linq.getObject(constructor.getParameters())
                        .where(param -> services.containsKey(param.getType()) || singletonServices.containsKey(param.getType()))
                            .count() == constructor.getParameters().length);

        if (validConstructor == null)
            throw new IllegalArgumentException();

        System.out.println(validConstructor.getParameters().length);

        return null;
    }

    private Set<Object> createSingletonServices() {
        return null;
    }
}
