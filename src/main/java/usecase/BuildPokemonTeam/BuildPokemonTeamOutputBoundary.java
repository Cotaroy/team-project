package usecase.BuildPokemonTeam;

/**
 * The output boundary for the BuildPokemonTeam Use Case.
 */
public interface BuildPokemonTeamOutputBoundary {
    /**
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
     * Switches to PokemonLookup View.
     * @param index the index of the Pokémon we are trying to change
     */
    void switchToPokemonLookupView(int index);
}
