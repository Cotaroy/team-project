package usecase.lookup;

import java.io.IOException;

import entity.Pokemon;

public interface PokemonLookupDataAccessInterface {
    /**
     * Gets the provided Pokemon.
     * @param name the Pokemon to get.
     * @return returns.
     * @throws IOException throws an exception.
     * @throws PokemonNotFoundException throws an exception.
     */
    Pokemon getPokemon(String name) throws IOException, PokemonNotFoundException;

    class PokemonNotFoundException extends Exception {
        public PokemonNotFoundException(String pokemonName) {
            super(pokemonName + " not found");
        }
    }

}
