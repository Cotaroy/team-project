package interface_adapter.pokemon_lookup;

import entity.Pokemon;

public class PokemonLookupState {
    private String pokemonName = "";
    private String pokemonNameError;

    private Pokemon displayPokemon;

    public String getPokemonName() {
        return pokemonName;
    }

    public String getPokemonNameError() {
        return pokemonNameError;
    }

    public Pokemon getDisplayPokemon() {
        return displayPokemon;
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

    @Override
    public String toString() {
        return "PokemonLookupState{"
                + "pokemonName='" + pokemonName + '\''
                + ", pokemonNameError='" + pokemonNameError + '\''
                + '}';
    }
}
