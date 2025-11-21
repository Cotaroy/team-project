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
    }

    @Override
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPresenter.switchToTeamBuilderView(index,pokemon);
    }

    private Type getType(int typeID, OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/type/" + typeID)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                userPresenter.prepareFailView("Type not found: " + typeID);
            }
            String responseBody =  response.body().string();
            JSONObject json = new JSONObject(responseBody);

            String name = json.getString("name");

            json = json.getJSONObject("damage_relations");

            JSONArray doubleDamageFromJSON = json.getJSONArray("double_damage_from");
            JSONArray doubleDamageToJSON = json.getJSONArray("double_damage_to");
            JSONArray halfDamageFromJSON = json.getJSONArray("half_damage_from");
            JSONArray noDamageFromJSON = json.getJSONArray("no_damage_from");

            HashSet<String> doubleDamageTo = getTypeNames(doubleDamageToJSON);
            HashSet<String> strengths = new HashSet<>(doubleDamageTo);

            HashSet<String> doubleDamageFrom = getTypeNames(doubleDamageFromJSON);
            HashSet<String> weaknesses = new HashSet<>(doubleDamageFrom);

            HashSet<String> halfDamageFrom = getTypeNames(halfDamageFromJSON);
            HashSet<String> noDamageFrom = getTypeNames(noDamageFromJSON);
            HashSet<String> resistances = new HashSet<>(halfDamageFrom);
            resistances.addAll(noDamageFrom);

            return new Type(name, typeID, strengths, weaknesses, resistances);
        }
    }

    private static HashSet<String> getTypeNames(JSONArray typeList) {
        HashSet<String> types = new HashSet<>();
        for(int i = 0; i < typeList.length(); i++) {
            types.add(typeList.getJSONObject(i).getString("name"));
        }
        return types;
    }
}



