package usecase.filter;

import java.util.List;

public class FilterPokemonOutputData {

    private final String filterCategory;
    private final String filterValue;
    private final List<String> pokemonNames;

    public FilterPokemonOutputData(String filterCategory, String filterValue, List<String> pokemonNames) {
        this.filterCategory = filterCategory;
        this.filterValue = filterValue;
        this.pokemonNames = pokemonNames;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public List<String> getPokemonNames() {
        return pokemonNames;
    }
}