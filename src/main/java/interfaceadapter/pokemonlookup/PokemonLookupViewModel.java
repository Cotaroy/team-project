package interfaceadapter.pokemonlookup;

import interfaceadapter.ViewModel;

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
    public static final String[] EGG_GROUPS = {    "Monster", "HumanLike", "Water 1", "Water 2", "Water 3",
            "Bug", "Flying", "Field", "Fairy", "Grass", "Dragon", "Amorphous", "Mineral", "Ditto", "Undiscovered"};
    public static final String[] REGIONS = {"national", "kanto", "original-johto", "hoenn",
            "original-sinnoh", "extended-sinnoh", "original-unova",
            "updated-unova", "conquest-gallery", "kalos-central",
            "kalos-coastal", "kalos-mountain", "updated-hoenn",
            "original-alola", "original-melemele", "original-akala",
            "original-ulaula", "original-poni", "updated-alola",
            "updated-melemele", "updated-akala", "updated-ulaula",
            "updated-poni", "letsgo-kanto", "galar", "isle-of-armor",
            "crown-tundra", "hisui", "paldea"};

    public PokemonLookupViewModel() {
        super("pokemon lookup");
        setState(new PokemonLookupState());
    }
}
