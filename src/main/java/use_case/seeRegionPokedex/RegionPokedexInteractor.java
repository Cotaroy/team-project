package use_case.seeRegionPokedex;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.io.IOException;

import entity.Pokemon;

public class RegionPokedexInteractor implements RegionPokedexInputBoundary {
    private final RegionPokedexDataAccessInterface dataAccess;
    private final RegionPokedexOutputBoundary userPresenter;

    public RegionPokedexInteractor(RegionPokedexDataAccessInterface dataAccess, RegionPokedexOutputBoundary userPresenter) {
        this.dataAccess = dataAccess;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(RegionPokedexInputData regionPokedexInputData) throws IOException {
        String name = regionPokedexInputData.getPokedex().toLowerCase();
        if ("".equals(name)) {
            userPresenter.prepareFailureView(("No pokemon game provided"));
            return;
        }
        ArrayList<String> pokemons = dataAccess.getPokedexData(name);
        RegionPokedexOutputData outputData = new RegionPokedexOutputData(pokemons);
        userPresenter.prepareSuccessView(outputData);

    }

}
