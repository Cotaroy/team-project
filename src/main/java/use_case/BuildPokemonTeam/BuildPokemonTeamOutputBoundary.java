package use_case.BuildPokemonTeam;

/**
 * The output boundary for the BuildPokemonTeam Use Case.
 */
public interface BuildPokemonTeamOutputBoundary {
    /**-are you
     * Prepares the success view for the BuildPokemonTeam Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BuildPokemonTeamOutputData outputData);

    /**
     * Prepares the failure view for the BuildPokemonTeam Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to PokemonLookup View
     * @param index the index of the Pokemon we are trying to change
     */
    void switchToPokemonLookupView(int index);
}