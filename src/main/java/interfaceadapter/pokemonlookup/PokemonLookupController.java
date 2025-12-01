package interfaceadapter.pokemonlookup;

import entity.Pokemon;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInputData;

import java.io.IOException;
import java.util.List;

import entity.Pokemon;
import usecase.filter.FilterPokemonInputBoundary;
import usecase.filter.FilterPokemonInputData;
import usecase.lookup.PokemonLookupDataAccessInterface;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInputData;
import usecase.seeRegionPokedex.RegionPokedexInputBoundary;
import usecase.seeRegionPokedex.RegionPokedexInputData;

public class PokemonLookupController {

    private final PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor;
    private final FilterPokemonInputBoundary filterPokemonInteractor;
    private final RegionPokedexInputBoundary regionPokedexInteractor;


    public PokemonLookupController(PokemonLookupInputBoundary userPokemonLookupUseCaseInteractor,
                                   FilterPokemonInputBoundary filterPokemonInteractor,
                                   RegionPokedexInputBoundary regionPokedexInteractor) {
        this.userPokemonLookupUseCaseInteractor = userPokemonLookupUseCaseInteractor;
        this.filterPokemonInteractor = filterPokemonInteractor;
        this.regionPokedexInteractor = regionPokedexInteractor;
    }

    /**
     * Switches to the Team builder view.
     * @param index The team slot.
     * @param pokemon The Pokemon to be set on the team.
     */
    public void switchToTeamBuilderView(int index, Pokemon pokemon) {
        userPokemonLookupUseCaseInteractor.switchToTeamBuilderView(index, pokemon);
    }

    /**
     * Executes.
     * @param pokemonName The Pokemon name to use.
     * @throws IOException throws an exception.
     * @throws PokemonLookupDataAccessInterface.PokemonNotFoundException throws an exception.
     */
    public void execute(String pokemonName) throws IOException,
            PokemonLookupDataAccessInterface.PokemonNotFoundException {
        final PokemonLookupInputData pokemonLookupInputData = new PokemonLookupInputData(pokemonName);

        userPokemonLookupUseCaseInteractor.execute(pokemonLookupInputData);
    }

    public void setFilterDisplay (String filterCategory, String filterValue) throws IOException {
        if (filterCategory == "pokedex"){
            final RegionPokedexInputData regionPokedexInputData = new RegionPokedexInputData(filterValue);
            regionPokedexInteractor.execute(regionPokedexInputData);
        }

        else{
            final FilterPokemonInputData filterInputData = new FilterPokemonInputData(filterCategory, filterValue);
            filterPokemonInteractor.execute(filterInputData);
        }
    }

}
