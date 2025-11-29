package usecase.PokemonLookup;

import entity.Pokemon;
import entity.Type;

import java.io.IOException;

public interface PokemonLookupDataAccessInterface {

    Pokemon getPokemon(String name) throws IOException;

}
