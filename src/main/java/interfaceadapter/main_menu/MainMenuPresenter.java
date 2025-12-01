package interfaceadapter.main_menu;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.teambuilder.TeamBuilderViewModel;
import usecase.main_menu.MainMenuOutputBoundary;
import usecase.main_menu.MainMenuOutputData;

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
