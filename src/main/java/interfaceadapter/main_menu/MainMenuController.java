package interfaceadapter.main_menu;

import usecase.main_menu.MainMenuInputBoundary;
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

