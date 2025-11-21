package use_case.BuildPokemonTeam;

import java.io.IOException;

/**
 * The BuildPokemonTeam Use Case.
 */

public interface BuildPokemonTeamInputBoundary {

    /**
     * Execute the add to Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case
     */
    void addToTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;
    /**
     * Execute the remove from Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case
     */
    void removeFromTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;
    /**
     * Execute the save to Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case
     */
    void saveTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;

    /**
     * Executes the switch to PokemonLookupView use case
     */
    void switchToPokemonLookupView(int index);
}