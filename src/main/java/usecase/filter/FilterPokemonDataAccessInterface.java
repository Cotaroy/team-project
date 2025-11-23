package usecase.filter;

import java.util.List;

/**
 * Gateway for retrieving Pokémon lists from PokeAPI.
 */
public interface FilterPokemonDataAccessInterface {

    /**
     * Check whether the given filter target (type / ability / egg-group)
     * exists in the PokeAPI.
     */
    boolean filterTargetExists(String filterCategory, String filterValue);

    /**
     * Retrieve a list of Pokémon names (all lowercase)
     * that match the given filter category and value.
     */
    List<String> getPokemonByFilter(String filterCategory, String filterValue);
}