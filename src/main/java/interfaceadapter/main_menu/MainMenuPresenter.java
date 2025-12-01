package interfaceadapter.main_menu;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.pokemonlookup.PokemonLookupViewModel;
import interfaceadapter.teambuilder.TeamBuilderViewModel;
import usecase.main_menu.MainMenuOutputBoundary;
import usecase.main_menu.MainMenuOutputData;
import view.PokemonLookupView;

public class MainMenuPresenter implements MainMenuOutputBoundary {
    private final MainViewModel menuViewModel;

    private final ViewManagerModel viewManagerModel;
    private final PokemonLookupViewModel pokemonLookupViewModel;
    private final TeamBuilderViewModel teamViewModel;

    public MainMenuPresenter(MainViewModel vm, ViewManagerModel viewManagerModel,
                             PokemonLookupViewModel pokemonLookupViewModel,
                             TeamBuilderViewModel teamViewModel) {
        this.menuViewModel = vm;
        this.viewManagerModel = viewManagerModel;
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.teamViewModel = teamViewModel;
    }


    @Override
    public void preparePokedexView(MainMenuOutputData data) {
        viewManagerModel.setState(pokemonLookupViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareTeamsView(MainMenuOutputData data) {
        viewManagerModel.setState(teamViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
