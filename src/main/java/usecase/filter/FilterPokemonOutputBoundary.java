package usecase.filter;
/**
 * Output Boundary for the "Filter Pokémon" use case.
 */
public interface FilterPokemonOutputBoundary {

    /**
     * Called when the Pokémon filter operation is successful.
     *
     * @param responseModel the output data containing the filter category, value,
     *                      and the list of matching Pokémon names.
     */
    void prepareSuccessView(FilterPokemonOutputData responseModel);

    /**
     * Called when the Pokémon filter operation fails.
     *
     * @param errorMessage the explanation of the failure.
     */
    void prepareFailView(String errorMessage);
}