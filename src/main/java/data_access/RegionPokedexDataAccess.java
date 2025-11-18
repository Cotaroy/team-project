package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import use_case.seeRegionPokedex.RegionPokedexDataAccessInterface;

public class RegionPokedexDataAccess implements RegionPokedexDataAccessInterface {
    private final OkHttpClient client = new OkHttpClient();
    @Override
    public ArrayList<String> getPokedexData(String pokedexName) throws IOException {

        System.out.println("calling api for pokedex: " + pokedexName);
        Request request1 = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokedex/" + pokedexName.toLowerCase())
                .build();

        try (Response response = client.newCall(request1).execute()) {
            System.out.println("Response code: " + response.code());
            if (!response.isSuccessful()) {
                throw new IOException("Pokdex not found: " + pokedexName);
            }
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray entries = json.getJSONArray("pokemon_entries");

            ArrayList<String> pokemons = new ArrayList<>();

            for (int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                JSONObject species = entry.getJSONObject("pokemon_species");
                String pokemonName = species.getString("name");
                pokemons.add(pokemonName);
            }

            return pokemons;
        }



    }
}
