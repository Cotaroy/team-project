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
        String name = pokemonLookupInputData.getName().toLowerCase();
        if ("".equals(name)) {
            userPresenter.prepareFailView("No Pokemon name provided.");
            return;
        } else {
            Pokemon pokemon = dataAccess.getPokemon(name);
            final PokemonLookupOutputData pokemonLookupOutputData =
                    new PokemonLookupOutputData(pokemon);
            userPresenter.prepareSuccessView(pokemonLookupOutputData);
        }
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPresenter.switchToTeamBuilderView(index,pokemon);
    }

    private static HashSet<String> getTypeNames(JSONArray typeList) {
        HashSet<String> types = new HashSet<>();
        for(int i = 0; i < typeList.length(); i++) {
            types.add(typeList.getJSONObject(i).getString("name"));
        }
        return types;
    }
}



