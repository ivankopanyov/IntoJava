package infrastructure.application;

import infrastructure.application.linq.Linq;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AppBuilder {

    private Class<? extends PresenterBase> startPresenter;

    private final Set<Class<? extends PresenterBase>> presenterClasses;

    private final Set<Class<? extends ViewBase>> viewsClasses;

    private final Map<Class<?>, Class<?>> servicesClasses;

    private final Map<Class<?>, Class<?>> singletonServicesClasses;

    private final Set<Object> singletonServices;

    public AppBuilder() {
        this.presenterClasses = new HashSet<>();
        this.viewsClasses = new HashSet<>();
        this.servicesClasses = new HashMap<>();
        this.singletonServicesClasses = new HashMap<>();
        this.singletonServices = new HashSet<>();
    }

    public PresenterBuilder addStartPresenter(Class<? extends PresenterBase> presenterClass) {
        this.startPresenter = presenterClass;
        presenterClasses.add(presenterClass);
        return new PresenterBuilder(this);
    }

    void addPresenter(Class<? extends PresenterBase> presenterClass) {
        presenterClasses.add(presenterClass);
    }

    void addView(Class<? extends ViewBase> viewClass) {
        viewsClasses.add(viewClass);
    }

    void addService(Class<?> abstractClass, Class<?>implementClass) {
        singletonServicesClasses.remove(abstractClass);
        servicesClasses.put(abstractClass, implementClass);
    }

    void addSingletonService(Class<?> abstractClass, Class<?>implementClass) {
        servicesClasses.remove(abstractClass);
        singletonServicesClasses.put(abstractClass, implementClass);
    }

    Application build() throws IllegalArgumentException {

        createSingletonServices();
        createServices();

        return null;
    }

    private void createSingletonServices() throws IllegalArgumentException {
        for (Class<?> serviceClass: singletonServicesClasses.keySet()) {

            if (Linq.getObject(singletonServices).first(s -> s.getClass().equals(serviceClass)) != null)
                continue;

            Object result = createSingletonService(serviceClass);
            if (result == null)
                throw new IllegalArgumentException();

            singletonServices.add(result);
        }
    }

    private void createServices() throws IllegalArgumentException {
        for (Class<?> serviceClass: servicesClasses.keySet()) {
            Object result = createService(serviceClass);
            if (result == null)
                throw new IllegalArgumentException();
        }
    }

    private Object createSingletonService(Class<?> serviceClass) {
        if (serviceClass == null || singletonServicesClasses.get(serviceClass) == null)
            return null;

        Constructor<?> constructor = getValidSingletonConstructor(serviceClass);

        if (constructor == null)
            return null;

        return createServiceObject(constructor);
    }

    private Object createService(Class<?> serviceClass) {
        if (serviceClass == null || servicesClasses.get(serviceClass) == null)
            return null;

        Constructor<?> constructor = getConstructor(serviceClass);

        if (constructor == null)
            return null;

        return createServiceObject(constructor);
    }

    private Object createServiceObject(Constructor<?> constructor) {

        Set<Object> params = new HashSet<>();

        for (var param: constructor.getParameterTypes()) {
            if (servicesClasses.containsKey(param)) {
                Object result = createService(param);
                if (result == null)
                    return null;

                params.add(result);
            } else {
                Object singletonService = Linq.getObject(singletonServices)
                        .first(s -> s.getClass().equals(param));

                if (singletonService == null)
                    singletonService = createSingletonService(param);

                if (singletonService == null)
                    return null;

                params.add(singletonService);
                singletonServices.add(singletonService);
            }
        }

        try {
            return constructor.newInstance(params.toArray());
        } catch (Exception e) {
            return null;
        }
    }

    private Constructor<?> getConstructor(Class<?> service) {
        var constructors = servicesClasses.get(service).getConstructors();

        if (constructors.length == 0)
            return null;

        return Linq.getObject(constructors)
                .orderBy(Constructor::getParameterCount)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(param -> servicesClasses.containsKey(param) ||
                                singletonServicesClasses.containsKey(param))
                        .count() == constructor.getParameterCount());
    }

    private Constructor<?> getValidSingletonConstructor(Class<?> service) {
        var constructors = singletonServicesClasses.get(service).getConstructors();

        if (constructors.length == 0)
            return null;

        return Linq.getObject(constructors)
                .orderBy(Constructor::getParameterCount)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(param -> servicesClasses.containsKey(param) ||
                                (singletonServicesClasses.containsKey(param) &&
                                isValidSingletonConstructor(singletonServicesClasses.get(service),
                                        singletonServicesClasses.get(param).getConstructors())))
                        .count() == constructor.getParameterCount());
    }

    private boolean isValidSingletonConstructor(Class<?> param, Constructor<?>[] constructors) {
        if (constructors.length == 0)
            throw new IllegalArgumentException();

        Constructor<?> validConstructor = Linq.getObject(constructors)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(p -> !p.equals(param))
                        .count() == constructor.getParameterCount());

        return validConstructor != null;
    }
}
