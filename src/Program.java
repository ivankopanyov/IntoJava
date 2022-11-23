import abstractions.Manager;
import abstractions.Validator;
import abstractions.View;
import infrastructure.application.Application;
import infrastructure.application.ApplicationBuilder;
import infrastructure.application.exceptions.ApplicationBuilderException;
import infrastructure.application.exceptions.PresenterHandlerException;
import model.UserValidatorV2;
import model.identity.UserManager;
import model.UserValidator;
import model.IdentityUser;
import presenter.ExitPresenter;
import presenter.IdentityPresenter;
import presenter.IdentityPresenterV2;
import view.ExitView;
import view.IdentityView;
import view.IdentityViewV2;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {

        try {
            Application app = new ApplicationBuilder()
                .addStartPresenter("main", IdentityPresenterV2.class)
                .addPresenter("exit", ExitPresenter.class)
                .addView(IdentityViewV2.class)
                .addView(ExitView.class)
                .addService(Manager.class, UserManager.class)
                .addService(Validator.class, UserValidatorV2.class)
                .build();

            app.run();

        } catch (ApplicationBuilderException | PresenterHandlerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
