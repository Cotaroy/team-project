package usecase.BuildPokemonTeam;

/**
 * The input data for the Pokemon Lookup Use Case.
 */

public class BuildPokemonTeamInputData {
    private final String name;

    public BuildPokemonTeamInputData(String name) {
        this.name = name;
    }

    String getName() { return name; }
}