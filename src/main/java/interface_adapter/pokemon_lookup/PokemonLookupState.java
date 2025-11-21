package interface_adapter.pokemon_lookup;

import entity.Pokemon;

public class PokemonLookupState {
    private String pokemonName = "";
    private String pokemonNameError;

    private Pokemon displayPokemon;

    private int index = -1;

    public String getPokemonName() {
        return pokemonName;
    }

    public String getPokemonNameError() {
        return pokemonNameError;
    }

    public Pokemon getDisplayPokemon() {
        return displayPokemon;
    }

    public int getIndex() {
        return index;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

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
