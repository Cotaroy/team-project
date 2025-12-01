package interfaceadapter.pokemonlookup;

import entity.Pokemon;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInputData;

import java.io.IOException;

import entity.Pokemon;
import usecase.lookup.PokemonLookupDataAccessInterface;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInputData;

public class PokemonLookupController {

    private final PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor;

    public PokemonLookupController(PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor) {
        this.userPokemonLookupUseCaseInteractor = userPokemonLookupUseCaseInteractor;
    }

    /**
     * Switches to the Team builder view.
     * @param index The team slot.
     * @param pokemon The Pokemon to be set on the team.
     */
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPokemonLookupUseCaseInteractor.switchToTeamBuilderView(index, pokemon);
    }

    /**
     * Executes.
     * @param pokemonName The Pokemon name to use.
     * @throws IOException throws an exception.
     * @throws PokemonLookupDataAccessInterface.PokemonNotFoundException throws an exception.
     */
    public void execute(String pokemonName) throws IOException,
            PokemonLookupDataAccessInterface.PokemonNotFoundException {
        final PokemonLookupInputData pokemonLookupInputData = new PokemonLookupInputData(pokemonName);

        userPokemonLookupUseCaseInteractor.execute(pokemonLookupInputData);
    }
}
