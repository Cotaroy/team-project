package interfaceadapter.pokemonlookup;

import java.io.IOException;

import entity.Pokemon;
import usecase.PokemonLookup.PokemonLookupInputBoundary;
import usecase.PokemonLookup.PokemonLookupInputData;

public class PokemonLookupController {

    private final PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor;

    public PokemonLookupController(PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor) {
        this.userPokemonLookupUseCaseInteractor = userPokemonLookupUseCaseInteractor;
    }

    /**
     * Switches to the team builder view.
     *
     * @param  index the index of the team slot that was selected.
     * @param  pokemon the selected Pokemon for the team.
     */
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPokemonLookupUseCaseInteractor.switchToTeamBuilderView(index, pokemon);
    }

    /**
     * Executes the Controller.
     * @param pokemonName the provided Pokemon name.
     * @throws IOException throws an IO Exception.
     * @throws PokemonLookupInputBoundary.PokemonNotFoundException throws an exception for when a Pokemon is not found.
     */

    public void execute(String pokemonName) throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        final PokemonLookupInputData pokemonLookupInputData = new PokemonLookupInputData(pokemonName);

        userPokemonLookupUseCaseInteractor.execute(pokemonLookupInputData);
    }
}
