package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.team_builder.TeamBuilderViewModel;
import use_case.main_menu.MainMenuOutputBoundary;
import use_case.main_menu.MainMenuOutputData;

public class MainMenuPresenter implements MainMenuOutputBoundary {
    private final MainViewModel menuViewModel;

    private final ViewManagerModel viewManagerModel;
//    private final Runnable switchToPokedex;
    private final TeamBuilderViewModel teamViewModel;

    public MainMenuPresenter(MainViewModel vm, ViewManagerModel viewManagerModel,
//                             Runnable switchToPokedex,
                             TeamBuilderViewModel teamViewModel) {
        this.menuViewModel = vm;
        this.viewManagerModel = viewManagerModel;
//        this.switchToPokedex = switchToPokedex;
        this.teamViewModel = teamViewModel;
    }

//    @Override
//    public void preparePokedexView(MainMenuOutputData data) {
//        switchToPokedex.run();
//    }

    @Override
    public void preparePokedexView(MainMenuOutputData data) {

    }

    @Override
    public void prepareTeamsView(MainMenuOutputData data) {
        viewManagerModel.setState(teamViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
