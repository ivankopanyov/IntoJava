import abstractions.Manager;
import abstractions.Validator;
import abstractions.View;
import model.identity.UserManager;
import model.UserValidator;
import model.IdentityUser;
import presenter.IdentityPresenter;
import view.IdentityView;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
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
