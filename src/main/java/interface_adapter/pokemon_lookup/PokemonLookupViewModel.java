package interface_adapter.pokemon_lookup;

import interface_adapter.ViewModel;

public class PokemonLookupViewModel extends ViewModel<PokemonLookupState> {

    public static final String TITLE_LABEL = "Pokemon Lookup";
    public static final String POKEMON_NAME_LABEL = "Pokemon Name";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String POKEMON_INFO_LABEL = "Pokemon Info";

    //for filter
    public static final String FILTER_BUTTON_LABEL = "Filter";
    public static final String FILTER_BY_LABEL = "Filter By";
    public static final String FILTER_VALUE_LABEL = "Value";

    public PokemonLookupViewModel() {
        super("pokemon lookup");
        setState(new PokemonLookupState());
    }
}
