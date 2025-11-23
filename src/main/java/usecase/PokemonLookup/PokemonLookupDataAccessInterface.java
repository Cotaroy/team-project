package usecase.PokemonLookup;

import java.io.IOException;

import entity.Pokemon;
import entity.Type;

public interface PokemonLookupDataAccessInterface {

    /**
     * Returns the passed-in Pokemon.
     * @param name The name of the Pokemon to return.
     * @return The Pokemon called from the API.
     * @throws IOException throws an exception.
     */
    Pokemon getPokemon(String name) throws IOException;

    /**
     * Returns the passed-in Type.
     * @param typeID The internal ID of the type to return.
     * @return The type called from the API.
     * @throws IOException throws an exception.
     */
    Type getType(int typeID) throws IOException;
}
