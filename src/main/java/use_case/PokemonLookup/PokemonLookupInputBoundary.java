package use_case.PokemonLookup;

import entity.Pokemon;

import java.io.IOException;

/**
 * The Pokemon Lookup Use Case.
 */

public interface PokemonLookupInputBoundary {

    /**
     * Execute the Pokemon Lookup Use Case.
     * @param pokemonLookupInputData the input data for this use case
     */
    void execute(PokemonLookupInputData pokemonLookupInputData) throws IOException, PokemonNotFoundException;

    /**
     * Executes the switchToTeamBuilderView use case
     * @param index index of Pokemon slot being changed in Team
     * @param pokemon Pokemon that will be replacing that slot
     */
    void switchToTeamBuilderView(int index, Pokemon pokemon);

    class PokemonNotFoundException extends Exception {
        public PokemonNotFoundException(String pokemonName) {
            super(pokemonName + " not found");
        }
    }
}
