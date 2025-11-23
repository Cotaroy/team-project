package usecase.seeRegionPokedex;
import java.util.ArrayList;

public class RegionPokedexOutputData {
    private final ArrayList<String> pokemonNames;

    public RegionPokedexOutputData(ArrayList<String> pokemonNames) {
        this.pokemonNames = pokemonNames;

    }
    public ArrayList<String> getPokemonNames() {
        return pokemonNames;
    }
}
