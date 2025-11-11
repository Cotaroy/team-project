package usecase.BuildPokemonTeam;

import java.io.IOException;

/**
 * The Pokemon Lookup Use Case.
 */

public interface BuildPokemonTeamInputBoundary {

    /**
     * Execute the Pokemon Lookup Use Case.
     * @param pokemonLookupInputData the input data for this use case
     */

    void execute(BuildPokemonTeamInputData pokemonLookupInputData) throws IOException;
}