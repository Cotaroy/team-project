package usecase.BuildPokemonTeam;

/**
 * The output boundary for the Pokemon Lookup Use Case.
 */
public interface BuildPokemonTeamOutputBoundary {
    /**
     * Prepares the success view for the Pokemon Lookup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BuildPokemonTeamOutputData outputData);

    /**
     * Prepares the failure view for the Pokemon Lookup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}