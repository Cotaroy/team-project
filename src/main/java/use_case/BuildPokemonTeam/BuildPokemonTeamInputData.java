package use_case.BuildPokemonTeam;

import entity.Team;

/**
 * The input data for the BuildPokemonTeam Use Case.
 */

public class BuildPokemonTeamInputData {
    private final int index;
    private final String name;
    private final Team team;


    public BuildPokemonTeamInputData(String name, Team team, int index) {
        this.index = index;
        this.name = name;
        this.team = team;
    }

    public BuildPokemonTeamInputData(String name, Team team) {
        this.name = name;
        this.team = team;
        index = -1;
    }


    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Team getTeam() {
        return team;
    }
}