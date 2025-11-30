package interface_adapter.pokemon_lookup;

import interface_adapter.ViewModel;

public class PokemonLookupViewModel extends ViewModel<PokemonLookupState> {

    public static final String TITLE_LABEL = "Pokemon Lookup";
    public static final String POKEMON_NAME_LABEL = "Pokemon Name";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String SAVE_TO_TEAM_LABEL = "Save to Team";
    public static final String POKEMON_INFO_LABEL = "Pokemon Info";

    public static final String FILTER_BY_LABEL = "filter by:";
    public static final String FILTER_VALUE_LABEL = "filter value:";
    public static final String FILTER_BUTTON_LABEL = "filter";
    public static final String[] FILTERS = {"type", "ability", "egg-group", "move", "pokedex"};
    public static final String[] TYPE_VALUES = {"water", "Fire", "Grass", "Electric", "Normal", "Ghost", "Fighting",
                                                "Dark", "Psychic", "Fairy", "Poison", "Flying", "Steel", "Rock", "Ground",
                                                "Ice", "Dragon", "Bug"};

    public PokemonLookupViewModel() {
        super("pokemon lookup");
        setState(new PokemonLookupState());
    }
}
