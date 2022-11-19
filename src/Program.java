import abstractions.Manager;
import abstractions.Validator;
import abstractions.View;
import model.identity.UserManager;
import model.UserValidator;
import model.IdentityUser;
import presenter.IdentityPresenter;
import view.IdentityView;

public class Program {
    public static void main(String[] args) {

        Validator<IdentityUser> validator = new UserValidator<>(
                null,
                null,
                null,
                "Пароль должен содержать:\n\tминимум одну цифру\n\tминимум одну латинскую букву в нижнем регистре\n\tминимум одну латинскую букву в верхнем регистре\n\tминимум один специальный символ\n\tминимум 8 символов"
                );

        Manager<IdentityUser> userManager = new UserManager<>(validator);
        View view = new IdentityView();
        IdentityPresenter presenter = new IdentityPresenter(userManager, view);
        presenter.run();
    }
}
