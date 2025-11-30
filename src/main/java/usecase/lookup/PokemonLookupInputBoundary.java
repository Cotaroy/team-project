package usecase.lookup;

import java.io.IOException;

import entity.Pokemon;
import usecase.lookup.PokemonLookupDataAccessInterface.PokemonNotFoundException;

/**
 * The Pokemon Lookup Use Case.
 */

public interface PokemonLookupInputBoundary {

    /**
     * Execute the Pokemon Lookup Use Case.
     * @param pokemonLookupInputData the input data for this use case.
     * @throws IOException throws an exception.
     * @throws PokemonNotFoundException throws an exception.
     */
    void execute(PokemonLookupInputData pokemonLookupInputData) throws IOException, PokemonNotFoundException;

    /**
     * Switches to the Team builder view.
     * @param index The team slot.
     * @param pokemon The Pokemon to be set on the team.
     */
    void switchToTeamBuilderView(int index, Pokemon pokemon);

}
