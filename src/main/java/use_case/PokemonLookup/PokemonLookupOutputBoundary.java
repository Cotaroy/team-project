package use_case.PokemonLookup;


import entity.Pokemon;

/**
 * The output boundary for the Pokemon Lookup Use Case.
 */
public interface PokemonLookupOutputBoundary {
    /**
     * Prepares the success view for the Pokemon Lookup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(PokemonLookupOutputData outputData);

    /**
     * Prepares the failure view for the Pokemon Lookup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to TeamBuilder View
     * @param index the index of the Pokemon we are replacing
     * @param pokemon the Pokemon that will be placed at the index
     */
    void switchToTeamBuilderView(int index, Pokemon pokemon);

}
