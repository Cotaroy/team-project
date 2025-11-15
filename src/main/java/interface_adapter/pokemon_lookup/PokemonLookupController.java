package interface_adapter.pokemon_lookup;

import use_case.PokemonLookup.PokemonLookupInputBoundary;
import use_case.PokemonLookup.PokemonLookupInputData;

import java.io.IOException;

public class PokemonLookupController {

    private final PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor;

    public PokemonLookupController(PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor) {
        this.userPokemonLookupUseCaseInteractor = userPokemonLookupUseCaseInteractor;
    }

    public void execute(String pokemonName) throws IOException {
        final PokemonLookupInputData pokemonLookupInputData = new PokemonLookupInputData(pokemonName);

        userPokemonLookupUseCaseInteractor.execute(pokemonLookupInputData);
    }
}
