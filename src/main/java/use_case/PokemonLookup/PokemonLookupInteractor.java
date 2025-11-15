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

    public PokemonLookupInteractor(PokemonLookupOutputBoundary pokemonLookupOutputBoundary,
                                   Pokemon Pokemon) {
        this.userPresenter = pokemonLookupOutputBoundary;
       this.pokemon = Pokemon;
    }

    @Override
    public void execute(PokemonLookupInputData pokemonLookupInputData) throws IOException, PokemonNotFoundException {
        String name = pokemonLookupInputData.getName().toLowerCase();
        if ("".equals(name)) {
            userPresenter.prepareFailView("No Pokemon name provided.");
        } else {
            name = name.replace(" ", "-");
            ArrayList<String> hyphens = new ArrayList<>(Arrays.asList(
                    "ho-oh", "jangmo-o", "kommo-o", "hakamo-o", "porygon-z",
                    "ting-lu", "chi-yu", "wo-chien", "chien-pao"
            ));
            OkHttpClient client;
            Request request1;
            Request request2;
            if (name.split("-").length == 1) {

                client = new OkHttpClient();

                request1 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon/" + name)
                        .build();

                request2 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon-species/" + name)
                        .build();

            }
            if (name.split("-").length == 2 && hyphens.contains(name)) {
                client = new OkHttpClient();

                request1 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon/" + name)
                        .build();

                request2 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon-species/" + name)
                        .build();
            }
            else {
                client = new OkHttpClient();

                request1 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon/" + name)
                        .build();

                int index = name.indexOf("-");
                if (index != -1) {
                    name = name.substring(0, index);
                }

                request2 = new Request.Builder()
                        .url("https://pokeapi.co/api/v2/pokemon-species/" + name)
                        .build();
            }

            try (Response response = client.newCall(request1).execute()) {
                if (!response.isSuccessful()) {
                    userPresenter.prepareFailView("Pokemon not found: " + name);
                    throw new PokemonNotFoundException(name);
                }

                try (Response response2 = client.newCall(request2).execute()) {
                    if (!response2.isSuccessful()) {
                        userPresenter.prepareFailView("Pokemon not found: " + name);
                        throw new PokemonNotFoundException(name);
                    }

                    // Parse the response JSON
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    String responseBody2 = response2.body().string();
                    JSONObject json2 = new JSONObject(responseBody2);

                    String pokename = json.getString("name");
                    String ptype1 = json.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("url");
                    String[] atype1 = ptype1.split("/");
                    int type1ID = Integer.parseInt(atype1[atype1.length - 1]);

                    Type type1 = getType(type1ID, client);

                    int type2ID = 0;
                    Type type2 = null;
                    if (json.getJSONArray("types").length() == 2) {
                        String ptype2 = json.getJSONArray("types").getJSONObject(1).getJSONObject("type").getString("url");
                        String[] atype2 = ptype2.split("/");
                        type2ID = Integer.parseInt(atype2[atype2.length - 1]);
                        type2 = getType(type2ID, client);

                    }
                    ArrayList<Integer> stats = new ArrayList<Integer>();
                    for (int count = 0; count < 6; count++) {
                        stats.add(json.getJSONArray("stats").getJSONObject(count).getInt("base_stat"));
                    }
                    ;
                    ArrayList<Integer> abilities = new ArrayList<Integer>();
                    int hidden = 0;
                    // a pokemon has 1 regular ability
                    if (json.getJSONArray("abilities").length() == 1) {
                        String pabil = json.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("url");
                        String[] aabil = pabil.split("/");
                        int abil = Integer.parseInt(aabil[aabil.length - 1]);
                        abilities.add(abil);
                    }
                    // a pokemon has 1 hidden ability and 1 regular ability
                    if (json.getJSONArray("abilities").length() == 2 && json.getJSONArray("abilities").getJSONObject(1).getInt("slot") == 3) {
                        String pabil = json.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("url");
                        String[] aabil = pabil.split("/");
                        int abil = Integer.parseInt(aabil[aabil.length - 1]);
                        abilities.add(abil);
                        String hiden = json.getJSONArray("abilities").getJSONObject(1).getJSONObject("ability").getString("url");
                        String[] ahiden = hiden.split("/");
                        hidden = Integer.parseInt(ahiden[ahiden.length - 1]);
                    }
                    // a pokemon has all regular and hidden abilities
                    if (json.getJSONArray("abilities").length() == 3) {
                        String pabil = json.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("url");
                        String[] aabil = pabil.split("/");
                        int abil = Integer.parseInt(aabil[aabil.length - 1]);
                        abilities.add(abil);
                        String pabil2 = json.getJSONArray("abilities").getJSONObject(1).getJSONObject("ability").getString("url");
                        String[] aabil2 = pabil2.split("/");
                        int abil2 = Integer.parseInt(aabil2[aabil2.length - 1]);
                        abilities.add(abil2);
                        String hiden = json.getJSONArray("abilities").getJSONObject(2).getJSONObject("ability").getString("url");
                        String[] ahiden = hiden.split("/");
                        hidden = Integer.parseInt(ahiden[ahiden.length - 1]);
                    }
                    ArrayList<Integer> moves = new ArrayList<Integer>();
                    for (int i = 0; i < json.getJSONArray("moves").length(); i++) {
                        String pmove = json.getJSONArray("moves").getJSONObject(i).getJSONObject("move").getString("url");
                        String[] amove = pmove.split("/");
                        int move = Integer.parseInt(amove[amove.length - 1]);
                        moves.add(move);
                    }
                    ArrayList<Integer> egggroup = new ArrayList<Integer>();
                    for (int i = 0; i < json2.getJSONArray("egg_groups").length(); i++) {
                        String pegg = json2.getJSONArray("egg_groups").getJSONObject(i).getString("url");
                        String[] aegg = pegg.split("/");
                        int egg = Integer.parseInt(aegg[aegg.length - 1]);
                        egggroup.add(egg);
                    }
                    ArrayList<Integer> pokedexes = new ArrayList<Integer>();
                    for (int i = 1; i < json2.getJSONArray("pokedex_numbers").length(); i++) {
                        String pdex = json2.getJSONArray("pokedex_numbers").getJSONObject(i).getJSONObject("pokedex").getString("url");
                        String[] adex = pdex.split("/");
                        int dex = Integer.parseInt(adex[adex.length - 1]);
                        pokedexes.add(dex);
                    }
                    String sprite = json.getJSONObject("sprites").getString("front_default");
//                    Pokemon Pokemon = new Pokemon(pokename, type1, type2, stats, abilities, hidden, moves, egggroup, pokedexes);
                    pokemon.setName(pokename);
                    pokemon.setType1(type1);
                    pokemon.setType2(type2);
                    pokemon.setStats(stats);
                    pokemon.setAbilities(abilities);
                    pokemon.setHidden(hidden);
                    pokemon.setMoves(moves);
                    pokemon.setEgggroup(egggroup);
                    pokemon.setPokedexes(pokedexes);
                    pokemon.setSprite(sprite);
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



