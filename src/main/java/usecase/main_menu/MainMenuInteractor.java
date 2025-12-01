package usecase.main_menu;

public class MainMenuInteractor implements MainMenuInputBoundary {
    public final MainMenuOutputBoundary presenter;

    public MainMenuInteractor(MainMenuOutputBoundary presenter){
        this.presenter = presenter;
    }

    @Override
    public void switchToPokedex() {
        presenter.preparePokedexView(new MainMenuOutputData());
    }

    public void switchToTeams() {
        presenter.prepareTeamsView(new MainMenuOutputData());
    }
}
