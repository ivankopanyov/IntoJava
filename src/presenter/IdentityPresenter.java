package presenter;

import abstractions.Manager;
import abstractions.View;
import exceptions.IdentityException;
import model.IdentityUser;

public class IdentityPresenter {
    private final Manager<IdentityUser> manager;

    private final View view;

    private IdentityUser current;

    public IdentityPresenter(Manager<IdentityUser> manager, View view) {
        this.manager = manager;
        this.view = view;
        current = null;
    }

    public void run() {
    }
}
