package usecase.lookup;

import entity.Pokemon;
import entity.Type;

import java.io.IOException;

public interface PokemonLookupDataAccessInterface {

    Pokemon getPokemon(String name) throws IOException;

    Type getType(int typeID) throws IOException;
}
