package usecase.lookup;

import entity.Pokemon;

import java.io.IOException;

public interface PokemonLookupDataAccessInterface {

    Pokemon getPokemon(String name) throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException;

}
