package usecase.seeRegionPokedex;

public class RegionPokedexInputData {
    private final String pokedex;

    public RegionPokedexInputData(String pokedex) {
        this.pokedex = pokedex;
    }
    String getPokedex() {
        return pokedex;
    }
}
