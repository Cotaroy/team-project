package usecase.filter;
/**
 * Input Boundary for the "Filter Pokémon" use case.
 */
public interface FilterPokemonInputBoundary {
    /**
     * Executes the Filter Pokémon use case with the given input data.
     *
     * @param inputData contains the filter category and value provided by the user.
     */
    void execute(FilterPokemonInputData inputData);
}
