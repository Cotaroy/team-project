package use_case.PokemonLookup;
import entity.Type;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.Pokemon;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
//            ;
//            final PokemonLookupOutputData pokemonLookupOutputData =
//                    new PokemonLookupOutputData(Pokemon);
//            userPresenter.prepareSuccessView(pokemonLookupOutputData);

        }



