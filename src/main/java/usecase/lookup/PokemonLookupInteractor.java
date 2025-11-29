package usecase.lookup;

import java.io.IOException;

import entity.Pokemon;

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

        boolean valid = true;
        Pokemon result = null;
        String error = null;

        // Validate name
        if (name == null || name.isEmpty()) {
            valid = false;
            error = "No Pokemon name provided.";
        }

        if (valid) {
            name = name.toLowerCase();

            try {
                result = dataAccess.getPokemon(name);
            }
            catch (PokemonLookupInputBoundary.PokemonNotFoundException exceptione) {
                valid = false;
                error = exceptione.getMessage();
            }
        }

        // Single exit point
        if (valid) {
            userPresenter.prepareSuccessView(new PokemonLookupOutputData(result));
        }
        else {
            userPresenter.prepareFailView(error);
        }
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon chosenpokemon) {
        userPresenter.switchToTeamBuilderView(index, chosenpokemon);
    }
}
