package usecase.lookup;

/**
 * The input data for the Pokemon Lookup Use Case.
 */

public class PokemonLookupInputData {
    private final String name;

    public PokemonLookupInputData(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
