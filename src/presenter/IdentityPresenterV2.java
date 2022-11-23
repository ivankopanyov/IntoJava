package presenter;

import abstractions.Manager;
import infrastructure.application.PresenterBase;
import infrastructure.application.PresenterHandler;
import infrastructure.application.exceptions.PresenterHandlerException;
import model.IdentityUser;
import view.IdentityViewV2;

/**
 * Класс, описывающий прзентер.
 */
public class IdentityPresenterV2 extends PresenterBase {

    /**
     * Объект предыдущей версии презентера.
     */
    private final IdentityPresenter presenter;

    /**
     * Инициализация объекта презентера.
     * @param presenterHandler Обработчик презентеров.
     * @param view Объект предствления.
     */
    public IdentityPresenterV2(PresenterHandler presenterHandler, Manager<IdentityUser> manager, IdentityViewV2 view) {
        super(presenterHandler);
        presenter = new IdentityPresenter(manager, view);
    }

    /**
     * Метод запуска презентера.
     */
    @Override
    public void run() throws PresenterHandlerException {
        presenter.run();
        PresenterHandler handler = getPresenterHandler();
        if (handler.hasKey("exit"))
            handler.setNextPresenter("exit");
    }
}
