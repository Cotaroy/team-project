package interface_adapter.main_menu;

import use_case.main_menu.MainMenuInputBoundary;
public class MainMenuController {
    private final MainMenuInputBoundary mainMenuInteractor;


    public MainMenuController(MainMenuInputBoundary interactor) {
        this.mainMenuInteractor = interactor;
    }

    public void switchToPokedex() {
        mainMenuInteractor.switchToPokedex();
    }

    public void switchToTeams() {
        mainMenuInteractor.switchToTeams();
    }
}

