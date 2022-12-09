package infrastructure.application;

import infrastructure.application.exceptions.ApplicationBuilderException;
import infrastructure.application.linq.Linq;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Класс, описывающий билдер для сборки приложения.
 */
public class ApplicationBuilder {

    /**
     * Ключ стартового презентера.
     */
    private String startPresenter;

    /**
     * Мэп, хранящий ключи и классы презентеров.
     */
    private final Map<String, Class<? extends PresenterBase>> presenterClasses;

    /**
     * Сэт, хранящий классы представлений.
     */
    private final Set<Class<? extends ViewBase>> viewsClasses;

    /**
     * Мэп, хранящий астрактные и имплементирующие классы сервесов.
     */
    private final Map<Class<?>, Class<?>> servicesClasses;

    /**
     * Мэп, хранящий астрактные и имплементирующие классы синглтон сервесов.
     */
    private final Map<Class<?>, Class<?>> singletonServicesClasses;

    /**
     * Сэт, хранящий объекты синглтон сервесов.
     */
    private final Set<Object> singletonServices;

    /**
     * Инициализация объекта билдера.
     */
    public ApplicationBuilder() {
        this.presenterClasses = new HashMap<>();
        this.viewsClasses = new HashSet<>();
        this.servicesClasses = new HashMap<>();
        this.singletonServicesClasses = new HashMap<>();
        this.singletonServices = new HashSet<>();
    }

    /**
     * Метод добавления стартового класса презентера.
     * @param key Ключ презентера.
     * @param presenterClass Класс презентера.
     * @return Вспомогательный билдер бля добавления презентеров.
     * @throws ApplicationBuilderException Возбуждается, если передан не иициализированный объект.
     */
    public PresenterBuilder addStartPresenter(String key, Class<? extends PresenterBase> presenterClass)
            throws ApplicationBuilderException {

        addPresenter(key, presenterClass);
        this.startPresenter = key;
        return new PresenterBuilder(this);
    }

    /**
     * Метод добавления класса презентера в коллекцию.
     * @param key Ключ презентера.
     * @param presenterClass Класс презентера.
     * @throws ApplicationBuilderException Возбуждается, если передан не иициализированный объект.
     */
    void addPresenter(String key, Class<? extends PresenterBase> presenterClass) throws ApplicationBuilderException {

        if (key == null)
            throw new ApplicationBuilderException("Передан нулевой ключ для Presenter в качестве параметра.");

        if (presenterClass == null)
            throw new ApplicationBuilderException("Передан нулевой класс Presenter в качестве параметра.");

        if (presenterClasses.containsKey(key))
            throw new ApplicationBuilderException("Presenter класс с таким ключом уже существует.");

        presenterClasses.put(key, presenterClass);
    }

    /**
     * Метод добавления класса представлния.
     * @param viewClass Класс представления.
     */
    void addView(Class<? extends ViewBase> viewClass) {
        viewsClasses.add(viewClass);
    }

    /**
     * Метод добаления класса сервиса.
     * @param abstractClass Абстрактный класс сервиса.
     * @param implementClass Имплементирующий класс сервиса.
     */
    void addService(Class<?> abstractClass, Class<?>implementClass) {
        singletonServicesClasses.remove(abstractClass);
        servicesClasses.put(abstractClass, implementClass);
    }


    /**
     * Метод добаления класса синглтон сервиса.
     * @param abstractClass Абстрактный класс сервиса.
     * @param implementClass Имплементирующий класс сервиса.
     */
    void addSingletonService(Class<?> abstractClass, Class<?>implementClass) {
        servicesClasses.remove(abstractClass);
        singletonServicesClasses.put(abstractClass, implementClass);
    }

    /**
     * Метод сборки приложения.
     * @return Объект приложения.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    Application build() throws ApplicationBuilderException {
        PresenterHandler presenterHandler = createPresenterHandler();
        return new Application(presenterHandler);
    }

    /**
     * Метод создания объекта обработки презентеров.
     * @return Объект обработчика презентеров.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private PresenterHandler createPresenterHandler() throws ApplicationBuilderException {
        singletonServicesClasses.put(PresenterHandler.class, PresenterHandler.class);
        PresenterHandler presenterHandler = new PresenterHandler();
        singletonServices.add(presenterHandler);
        for (String key: presenterClasses.keySet()) {
            Class<?> presenter = presenterClasses.get(key);
            PresenterBase result = (PresenterBase) buildObject(getConstructor(presenter, true), true);
            result.setPresenterHandler(presenterHandler);
            presenterHandler.addPresenter(key, result);
        }
        presenterHandler.setStartPresenter(startPresenter);
        return presenterHandler;
    }

    /**
     * Метод создания объекта класса.
     * @param constructor Конструктор класса.
     * @return Объект класса.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Object buildObject(Constructor<?> constructor) throws ApplicationBuilderException {
        return buildObject(constructor, false);
    }

    /**
     * Метод создания объекта класса.
     * @param constructor Конструктор класса.
     * @param checkView Делать проверку конструктора в классах представлений.
     * @return Объект класса.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Object buildObject(Constructor<?> constructor, boolean checkView) throws ApplicationBuilderException {

        List<Object> params = new ArrayList<>();

        for (var param: constructor.getParameterTypes()) {

            if (checkView && viewsClasses.contains(param)) {
                params.add(createObject(param));
                break;
            }

            if (servicesClasses.containsKey(param)) {

                if (servicesClasses.get(param) == null)
                    throw new ApplicationBuilderException("Сервис " + servicesClasses.get(param) +
                        "не содержит класса имплементации.");

                params.add(createObject(servicesClasses.get(param)));

            } else if (singletonServicesClasses.containsKey(param)) {

                if (singletonServicesClasses.get(param) == null)
                    throw new ApplicationBuilderException("Сервис " + singletonServicesClasses.get(param) +
                            "не содержит класса имплементации.");

                Object singletonService = getSingletonService(param);

                if (singletonService == null)
                    singletonService = createSingletonObject(singletonServicesClasses.get(param));

                params.add(singletonService);

            } else
                throw new ApplicationBuilderException("Класс " + param.getName() + " не зарегистрирован в качестве сервиса.");
        }

        try {
            return constructor.newInstance(params.toArray());
        } catch (Exception e) {
            throw new ApplicationBuilderException(e);
        }
    }

    /**
     * Метод создания объекта.
     * @param classObject Класс объекта.
     * @return Созданный объект.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Object createObject(Class<?> classObject) throws ApplicationBuilderException {
        Constructor<?> constructor = getConstructor(classObject);
        return buildObject(constructor);
    }

    /**
     * Метод создания объекта синглтон сервиса.
     * @param classObject Класс объекта.
     * @return Созданный объект.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Object createSingletonObject(Class<?> classObject) throws ApplicationBuilderException {
        Constructor<?> constructor = getSingletonConstructor(classObject);
        return buildObject(constructor);
    }

    /**
     * Метод поиска валидного конструктора
     * @param objectClass Класс для поиска конструктора.
     * @return Взвращает валидный конструктор.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Constructor<?> getConstructor(Class<?> objectClass) throws ApplicationBuilderException {
        return getConstructor(objectClass, false);
    }

    /**
     * Метод поиска валидного конструктора
     * @param objectClass Класс для поиска конструктора.
     * @param checkView Делать проверку в классах представлений.
     * @return Взвращает валидный конструктор.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Constructor<?> getConstructor(Class<?> objectClass, boolean checkView) throws ApplicationBuilderException {
        Constructor<?>[] constructors = objectClass.getConstructors();

        if (constructors.length == 0)
            throw new ApplicationBuilderException("Класс " + objectClass.getName() + " не содержит публичных конструкторов.");

        Constructor<?> result =  Linq.getObject(constructors)
                .orderBy(Constructor::getParameterCount)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(param -> servicesClasses.containsKey(param) ||
                                singletonServicesClasses.containsKey(param) ||
                                (checkView && viewsClasses.contains(param)))
                        .count() == constructor.getParameterCount());

        if (result == null)
            throw new ApplicationBuilderException("Класс " + objectClass.getName() +
                    " не содержит валидных конструкторов.");

        return result;
    }

    /**
     * Метод поиска валидного конструктора синглтон сервиса.
     * @param service Класс для поиска конструктора.
     * @return Взвращает валидный конструктор.
     * @throws ApplicationBuilderException Возбуждается, если класс имплементации не содержит валидных конструкторов.
     */
    private Constructor<?> getSingletonConstructor(Class<?> service) throws ApplicationBuilderException {
        Constructor<?>[] constructors = singletonServicesClasses.get(service).getConstructors();

        if (constructors.length == 0)
            throw new ApplicationBuilderException("Синглтон класс " + service.getName() + " не содержит публичных конструкторов.");

        Constructor<?> result = Linq.getObject(constructors)
                .orderBy(Constructor::getParameterCount)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(param -> servicesClasses.containsKey(param) ||
                                (singletonServicesClasses.containsKey(param) &&
                                isValidSingletonConstructor(singletonServicesClasses.get(service),
                                        singletonServicesClasses.get(param).getConstructors())))
                        .count() == constructor.getParameterCount());

        if (result == null)
            throw new ApplicationBuilderException("Синглтон класс " + service.getName() +
                    " не содержит валидных конструкторов.");

        return result;
    }

    /**
     * Метод проверки конструктора синглтон сервиса на валидность.
     * @param param Класс парамтра для проверки.
     * @param constructors Конструктор для поиска класса параметра.
     * @return Результат проверки.
     */
    private boolean isValidSingletonConstructor(Class<?> param, Constructor<?>[] constructors) {
        if (constructors.length == 0)
            return false;

        Constructor<?> validConstructor = Linq.getObject(constructors)
                .first(constructor -> Linq.getObject(constructor.getParameterTypes())
                        .where(p -> getSingletonService(param) != null || !p.equals(param))
                        .count() == constructor.getParameterCount());

        return validConstructor != null;
    }

    /**
     * Метод поиска созданного объекта синглтон сервиса.
     * @param serviceClass Класс синглтон сервиса.
     * @return Результат поиска.
     */
    private Object getSingletonService(Class<?> serviceClass) {
        return Linq.getObject(singletonServices).first(s -> s.getClass().equals(serviceClass));
    }
}
