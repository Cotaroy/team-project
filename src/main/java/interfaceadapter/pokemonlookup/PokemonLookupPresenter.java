package interfaceadapter.pokemonlookup;

import entity.Pokemon;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.teambuilder.TeamBuilderState;
import interfaceadapter.teambuilder.TeamBuilderViewModel;
import usecase.filter.FilterPokemonOutputBoundary;
import usecase.filter.FilterPokemonOutputData;
import usecase.lookup.PokemonLookupOutputBoundary;
import usecase.lookup.PokemonLookupOutputData;

public class PokemonLookupPresenter implements PokemonLookupOutputBoundary, FilterPokemonOutputBoundary {

    private final PokemonLookupViewModel pokemonLookupViewModel;
    private final TeamBuilderViewModel teamBuilderViewModel;
    private final ViewManagerModel viewManagerModel;

    public PokemonLookupPresenter(PokemonLookupViewModel pokemonLookupViewModel,
                                  TeamBuilderViewModel teamBuilderViewModel, ViewManagerModel viewManagerModel) {
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.teamBuilderViewModel = teamBuilderViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PokemonLookupOutputData outputData) {
        final PokemonLookupState pokemonLookupState = pokemonLookupViewModel.getState();
        pokemonLookupState.setDisplayPokemon(outputData.getPokemon());
        pokemonLookupViewModel.firePropertyChange();
    }

    @Override
    public void prepareSuccessView(FilterPokemonOutputData responseModel) {
        final PokemonLookupState pokemonLookupState = pokemonLookupViewModel.getState();
        pokemonLookupState.setFilteredDisplay(responseModel.getPokemonNames());
        pokemonLookupViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        final PokemonLookupState pokemonLookupState = pokemonLookupViewModel.getState();
        pokemonLookupState.setPokemonNameError(error);
        pokemonLookupViewModel.firePropertyChange();
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        final TeamBuilderState state = teamBuilderViewModel.getState();
        final Pokemon newPokemon = pokemon.getCopy();
        state.getTeam().setPokemon(newPokemon, index);
        viewManagerModel.setState(teamBuilderViewModel.getViewName());
        viewManagerModel.firePropertyChange();
        teamBuilderViewModel.firePropertyChange();
    }
}
