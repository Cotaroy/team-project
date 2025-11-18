package use_case.PokemonLookup;

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

    class PokemonNotFoundException extends Exception {
        public PokemonNotFoundException(String pokemonName) {
            super(pokemonName + " not found");
        }
    }
}
