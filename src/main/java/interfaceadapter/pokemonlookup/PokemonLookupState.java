package interfaceadapter.pokemonlookup;

import entity.Pokemon;

import java.util.List;

public class PokemonLookupState {
    private String pokemonName = "";
    private String pokemonNameError;

    private String filterType = "";
    private String filterValue = "";

    private Pokemon displayPokemon;
    private List<String> filteredPokemonList;

    private int index = -1;

    public String getPokemonName() {
        return pokemonName;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public String getPokemonNameError() {
        return pokemonNameError;
    }

    public Pokemon getDisplayPokemon() {
        return displayPokemon;
    }

    public List<String> getFilteredPokemonList() {return filteredPokemonList;}

    public int getIndex() {
        return index;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public void setFilteredDisplay(List<String> filteredPokemonList) {this.filteredPokemonList = filteredPokemonList;}

    public void setPokemonNameError(String pokemonNameError) {
        this.pokemonNameError = pokemonNameError;
    }

    public void setDisplayPokemon(Pokemon displayPokemon) {
        this.displayPokemon = displayPokemon;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PokemonLookupState{"
                + "pokemonName='" + pokemonName + '\''
                + ", pokemonNameError='" + pokemonNameError + '\''
                + '}';
    }
}
