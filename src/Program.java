import abstractions.Manager;
import abstractions.Validator;
import abstractions.View;
import infrastructure.application.AppBuilder;
import infrastructure.application.ServiceBuilder;
import model.identity.UserManager;
import model.UserValidator;
import model.IdentityUser;
import presenter.IdentityPresenter;
import view.IdentityView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        new AppBuilder()
                .addStartPresenter(Presenter1.class)
                .addView(View1.class)
                .addView(View2.class)
                .addService(TestService1.class, TestService1Impl.class)
                .addService(TestService2.class, TestService2Impl.class)
                .build();



        Validator<IdentityUser> validator = new UserValidator<>(
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
                "^.{8,}$",
                null,
                "\nПароль должен содержать минимум 8 символов"
                );

        Manager<IdentityUser> userManager = new UserManager<>(validator);
        View view = new IdentityView();
        IdentityPresenter presenter = new IdentityPresenter(userManager, view);
        presenter.run();
    }
}
