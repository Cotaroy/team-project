package usecase.seeRegionPokedex;

import java.util.ArrayList;

import java.io.IOException;

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


        if (name.isEmpty()) {
            userPresenter.prepareFailureView(("No pokedex provided"));
            return;
        }
        try {
            ArrayList<String> pokemons = dataAccess.getPokedexData(name);
            RegionPokedexOutputData outputData = new RegionPokedexOutputData(pokemons);
            userPresenter.prepareSuccessView(outputData);
        } catch (IOException e) {
            userPresenter.prepareFailureView("Region not found");
        }


    }

}
