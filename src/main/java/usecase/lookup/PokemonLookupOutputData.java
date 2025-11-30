package usecase.lookup;

import entity.Pokemon;

public class PokemonLookupOutputData {
    private final Pokemon pokemon;

    public PokemonLookupOutputData(Pokemon Pokemon) {
        this.pokemon = Pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

}
