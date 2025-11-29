package data_access;

import entity.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PokemonMap {

    private final Map<String, Pokemon> pokemonMap = new HashMap<>();

    public Pokemon getPokemon(String name) {
        return pokemonMap.get(name.toLowerCase());
    }

    public PokemonMap(AbilityMap abilitymap, MoveMap movemap) {
        try {
            String jsonText = new String(
                    getClass().getClassLoader().getResourceAsStream("pokemon.json").readAllBytes()
            );

            JSONObject root = new JSONObject(jsonText);

            for (String pokename : root.keySet()) {

                JSONObject entry = root.getJSONObject(pokename);
                JSONObject json = entry.getJSONObject("pokemon");
                JSONObject json2 = entry.getJSONObject("species");

                // -------------------------
                // TYPE PARSING
                // -------------------------
                String ptype1 = json.getJSONArray("types")
                        .getJSONObject(0)
                        .getJSONObject("type")
                        .getString("url");
                int type1ID = extractId(ptype1);
                Type type1 = getType(type1ID);

                Type type2 = null;
                if (json.getJSONArray("types").length() == 2) {
                    String ptype2 = json.getJSONArray("types")
                            .getJSONObject(1)
                            .getJSONObject("type")
                            .getString("url");
                    int type2ID = extractId(ptype2);
                    type2 = getType(type2ID);
                }

                // -------------------------
                // STATS
                // -------------------------
                ArrayList<Integer> stats = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    stats.add(json.getJSONArray("stats")
                            .getJSONObject(i)
                            .getInt("base_stat"));
                }

                // -------------------------
                // ABILITIES
                // -------------------------
                ArrayList<Ability> abilities = new ArrayList<>();
                Ability hidden = null;
                JSONArray a = json.getJSONArray("abilities");

                if (a.length() == 1) {
                    int id = extractId(a.getJSONObject(0).getJSONObject("ability").getString("url"));
                    abilities.add(abilitymap.getAbility(id));

                } else if (a.length() == 2 && a.getJSONObject(1).getInt("slot") == 3) {
                    int id1 = extractId(a.getJSONObject(0).getJSONObject("ability").getString("url"));
                    abilities.add(abilitymap.getAbility(id1));

                    int hidId = extractId(a.getJSONObject(1).getJSONObject("ability").getString("url"));
                    hidden = abilitymap.getAbility(hidId);

                } else if (a.length() == 3) {
                    int id1 = extractId(a.getJSONObject(0).getJSONObject("ability").getString("url"));
                    int id2 = extractId(a.getJSONObject(1).getJSONObject("ability").getString("url"));
                    int hidId = extractId(a.getJSONObject(2).getJSONObject("ability").getString("url"));

                    abilities.add(abilitymap.getAbility(id1));
                    abilities.add(abilitymap.getAbility(id2));
                    hidden = abilitymap.getAbility(hidId);
                }

                // -------------------------
                // MOVES
                // -------------------------
                ArrayList<Move> moves = new ArrayList<>();
                JSONArray moveArray = json.getJSONArray("moves");

                for (int i = 0; i < moveArray.length(); i++) {
                    int moveId = extractId(
                            moveArray.getJSONObject(i)
                                    .getJSONObject("move")
                                    .getString("url")
                    );
                    Move move = movemap.getMove(moveId);
                    if (move != null) moves.add(move);
                }

                // -------------------------
                // EGG GROUPS (species)
                // -------------------------
                ArrayList<Integer> eggGroups = new ArrayList<>();
                JSONArray eggs = json2.getJSONArray("egg_groups");
                for (int i = 0; i < eggs.length(); i++) {
                    eggGroups.add(extractId(eggs.getJSONObject(i).getString("url")));
                }

                // -------------------------
                // POKEDEX NUMBERS (species)
                // -------------------------
                ArrayList<Integer> pokedexes = new ArrayList<>();
                JSONArray dexNums = json2.getJSONArray("pokedex_numbers");
                for (int i = 0; i < dexNums.length(); i++) {
                    int dexId = extractId(
                            dexNums.getJSONObject(i)
                                    .getJSONObject("pokedex")
                                    .getString("url")
                    );
                    pokedexes.add(dexId);
                }

                // -------------------------
                // SPRITE
                // -------------------------
                String sprite = json.getJSONObject("sprites")
                        .getJSONObject("other")
                        .getJSONObject("official-artwork")
                        .getString("front_default");

                Pokemon pokemon = new PokemonBuilder()
                        .setName(pokename)
                        .setType1(type1)
                        .setType2(type2)
                        .setStats(stats)
                        .setAbilities(abilities)
                        .setHidden(hidden)
                        .setMoves(moves)
                        .setEggGroups(eggGroups)
                        .setPokedexes(pokedexes)
                        .setSprite(sprite)
                        .build();;

                pokemonMap.put(pokename.toLowerCase(), pokemon);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int extractId(String url) {
        String[] arr = url.split("/");
        return Integer.parseInt(arr[arr.length - 1]);
    }

    public Type getType(int typeID) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/type/" + typeID)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);

            String name = json.getString("name");
            String sprite = json.getJSONObject("sprites").getJSONObject("generation-vi").getJSONObject("omega-ruby-alpha-sapphire").getString("name_icon");

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

            return new Type(name, typeID, strengths, weaknesses, resistances, sprite);
        }
    }

    private static HashSet<String> getTypeNames(JSONArray typeList) {
        HashSet<String> types = new HashSet<>();
        for (int i = 0; i < typeList.length(); i++) {
            types.add(typeList.getJSONObject(i).getString("name"));
        }
        return types;
    }
}
