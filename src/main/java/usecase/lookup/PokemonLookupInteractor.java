package usecase.lookup;

import org.json.JSONArray;

import entity.Pokemon;

import java.io.IOException;
import java.util.HashSet;

public class PokemonLookupInteractor implements PokemonLookupInputBoundary {
    private final PokemonLookupOutputBoundary userPresenter;
    private final Pokemon pokemon;
    private final PokemonLookupDataAccessInterface dataAccess;

    public PokemonLookupInteractor(PokemonLookupOutputBoundary pokemonLookupOutputBoundary,
                                   Pokemon Pokemon, PokemonLookupDataAccessInterface dataAccess) {
        this.userPresenter = pokemonLookupOutputBoundary;
        this.pokemon = Pokemon;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(PokemonLookupInputData pokemonLookupInputData) throws IOException {
        String name = pokemonLookupInputData.getName();
        if (name == null) {
            userPresenter.prepareFailView("No Pokemon name provided.");
            return;
        }

        name = name.toLowerCase();

        if ("".equals(name)) {
            userPresenter.prepareFailView("No Pokemon name provided.");
            return;
        }

        try {
            Pokemon pokemon = dataAccess.getPokemon(name);
            final PokemonLookupOutputData pokemonLookupOutputData =
                    new PokemonLookupOutputData(pokemon);
            userPresenter.prepareSuccessView(pokemonLookupOutputData);
        } catch (PokemonLookupInputBoundary.PokemonNotFoundException e) {
            // forward the exact message from the exception to the presenter
            userPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPresenter.switchToTeamBuilderView(index,pokemon);
    }
}



