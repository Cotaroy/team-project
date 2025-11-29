package usecase.filter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fetches Pokémon lists from PokeAPI for:
 * type, ability, egg group, move
 */
public class FilterPokemonDataAccess implements FilterPokemonDataAccessInterface {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public boolean filterTargetExists(String filterCategory, String filterValue) {
        String url = buildUrl(filterCategory, filterValue);
        if (url == null) {
            return false;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public List<String> getPokemonByFilter(String filterCategory, String filterValue) {
        String url = buildUrl(filterCategory, filterValue);
        if (url == null) {
            return Collections.emptyList();
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                return Collections.emptyList();
            }

            String body = response.body().string();
            JSONObject json = new JSONObject(body);

            switch (filterCategory) {
                case "type":
                    return parseType(json);
                case "ability":
                    return parseAbility(json);
                case "egg-group":
                    return parseEggGroup(json);
                case "move":
                    return parseMove(json);
                default:
                    return Collections.emptyList();
            }

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    //builder for URL

    private String buildUrl(String category, String valueRaw) {
        String value = normalize(valueRaw);
        if (value.isEmpty()) return null;

        if (category.equals("type")) {
            return BASE_URL + "type/" + value;
        } else if (category.equals("ability")) {
            return BASE_URL + "ability/" + value;
        } else if (category.equals("egg-group")) {
            return BASE_URL + "egg-group/" + value;
        } else if (category.equals("move")) {
            return BASE_URL + "move/" + value;
        } else {
            return null;
        }
    }

    // normalize user input to API
    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase().replace(" ", "-");
    }

    /**
     * type
     */
    private List<String> parseType(JSONObject json) {
        List<String> names = new ArrayList<>();
        JSONArray arr = json.getJSONArray("pokemon");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject pokemonObj = arr.getJSONObject(i).getJSONObject("pokemon");
            names.add(pokemonObj.getString("name").toLowerCase());
        }
        return names;
    }

    /**
     * ability
     * Includes all Pokémon with that ability (hidden or not).
     */
    private List<String> parseAbility(JSONObject json) {
        List<String> names = new ArrayList<>();
        JSONArray arr = json.getJSONArray("pokemon");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject entry = arr.getJSONObject(i).getJSONObject("pokemon");
            names.add(entry.getString("name").toLowerCase());
        }
        return names;
    }

    /**
     * egg-group
     */
    private List<String> parseEggGroup(JSONObject json) {
        List<String> names = new ArrayList<>();
        JSONArray arr = json.getJSONArray("pokemon_species");
        for (int i = 0; i < arr.length(); i++) {
            names.add(arr.getJSONObject(i).getString("name").toLowerCase());
        }
        return names;
    }

    /**
     * move
     */
    private List<String> parseMove(JSONObject json) {
        List<String> names = new ArrayList<>();
        JSONArray arr = json.getJSONArray("learned_by_pokemon");
        for (int i = 0; i < arr.length(); i++) {
            names.add(arr.getJSONObject(i).getString("name").toLowerCase());
        }
        return names;
    }
}
