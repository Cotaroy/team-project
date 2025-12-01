package usecase.BuildPokemonTeam;

import entity.Team;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The BuildPokemonTeam Use Case.
 */

public interface BuildPokemonTeamInputBoundary {

    /**
     * Execute the add to Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case.
     * @throws IOException if it does not work.
     */
    void addToTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;
    /**
     * Execute the remove from Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case.
     * @throws IOException if it does not work.
     */

    void removeFromTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;
    /**
     * Execute the save to Team Use Case.
     * @param BuildPokemonTeamInputData the input data for this use case
     * @throws IOException if it does not work.
     */
    void saveTeam(BuildPokemonTeamInputData BuildPokemonTeamInputData) throws IOException;

    /**
     * Executes the switch to PokemonLookupView use case.
     * @param index the index used to switch the view.
     */
    void switchToPokemonLookupView(int index);

    /**
     * returns all saved team names
     * @return ArrayList<String> names of saved teams
     */
    ArrayList<String> getAllTeamNames();

    /**
     * returns a saved team that's being loaded
     * @param teamName name of the team
     * @return Team the team being loaded
     */
    Team loadTeam(String teamName) throws IOException;

}
