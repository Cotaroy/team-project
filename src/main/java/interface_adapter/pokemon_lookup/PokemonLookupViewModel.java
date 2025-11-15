package interface_adapter.pokemon_lookup;

import interface_adapter.ViewModel;

public class PokemonLookupViewModel extends ViewModel<PokemonLookupState> {

    public static final String TITLE_LABEL = "Pokemon Lookup";
    public static final String POKEMON_NAME_LABEL = "Pokemon Name";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String POKEMON_INFO_LABEL = "Pokemon Info";

    public PokemonLookupViewModel() {
        super("pokemon lookup");
        setState(new PokemonLookupState());
    }
}
