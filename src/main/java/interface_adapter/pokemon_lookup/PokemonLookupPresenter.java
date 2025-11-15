package interface_adapter.pokemon_lookup;

import interface_adapter.ViewManagerModel;
import use_case.PokemonLookup.PokemonLookupOutputBoundary;
import use_case.PokemonLookup.PokemonLookupOutputData;

public class PokemonLookupPresenter implements PokemonLookupOutputBoundary {

    private final PokemonLookupViewModel pokemonLookupViewModel;
    private final ViewManagerModel viewManagerModel;

    public PokemonLookupPresenter(PokemonLookupViewModel pokemonLookupViewModel, ViewManagerModel viewManagerModel) {
        this.pokemonLookupViewModel = pokemonLookupViewModel;
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
}
