import controller.Controller;
import controller.implement.PersonController;
import repositories.Repository;
import repositories.implement.FileRepository;
import services.Mapper;
import services.implement.PersonMapper;
import view.View;
import view.implement.MainView;

public class Program {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) {
        Mapper mapper = new PersonMapper();
        Repository repository = new FileRepository("persons", mapper);
        View view = new MainView();
        Controller controller = new PersonController(view, repository, mapper);
        controller.start();
    }
}
