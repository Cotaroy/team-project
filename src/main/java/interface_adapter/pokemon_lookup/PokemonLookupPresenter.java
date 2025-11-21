package interface_adapter.pokemon_lookup;

import entity.Pokemon;
import interface_adapter.ViewManagerModel;
import interface_adapter.team_builder.TeamBuilderState;
import interface_adapter.team_builder.TeamBuilderViewModel;
import use_case.PokemonLookup.PokemonLookupOutputBoundary;
import use_case.PokemonLookup.PokemonLookupOutputData;

public class PokemonLookupPresenter implements PokemonLookupOutputBoundary {

    private final PokemonLookupViewModel pokemonLookupViewModel;
    private final TeamBuilderViewModel teamBuilderViewModel;
    private final ViewManagerModel viewManagerModel;

    public PokemonLookupPresenter(PokemonLookupViewModel pokemonLookupViewModel, TeamBuilderViewModel teamBuilderViewModel, ViewManagerModel viewManagerModel) {
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
    public void prepareFailView(String error) {
        final PokemonLookupState pokemonLookupState = pokemonLookupViewModel.getState();
        pokemonLookupState.setPokemonNameError(error);
        pokemonLookupViewModel.firePropertyChange();
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        TeamBuilderState state = teamBuilderViewModel.getState();
        Pokemon newPokemon = pokemon.getCopy();
        state.getTeam().setPokemon(newPokemon, index);
        viewManagerModel.setState(teamBuilderViewModel.getViewName());
        viewManagerModel.firePropertyChange();
        teamBuilderViewModel.firePropertyChange();
    }
}
