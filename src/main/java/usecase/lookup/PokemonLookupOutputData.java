package usecase.lookup;

import entity.Pokemon;

public class PokemonLookupOutputData {
    private final Pokemon Pokemon;

    public PokemonLookupOutputData(Pokemon Pokemon) {
        this.Pokemon = Pokemon;
    }

    public Pokemon getPokemon() {
        return Pokemon;
    }

}
