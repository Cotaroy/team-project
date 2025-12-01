package usecase.BuildPokemonTeam;

import entity.Team;

/**
 * The input data for the BuildPokemonTeam Use Case.
 */

public class BuildPokemonTeamInputData {
    private final int index;
    private final String name;
    private final Team selectedTeam;

    public BuildPokemonTeamInputData(String name, Team team, int index) {
        this.index = index;
        this.name = name;
        this.selectedTeam = team;
    }

    public BuildPokemonTeamInputData(String name, Team team) {
        this.name = name;
        this.selectedTeam = team;
        index = -1;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }
}
