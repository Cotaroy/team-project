package usecase.seeRegionPokedex;

public interface RegionPokedexOutputBoundary {
    void prepareSuccessView(RegionPokedexOutputData pokemonOutputData);
    void prepareFailView(String errorMessage);
}
