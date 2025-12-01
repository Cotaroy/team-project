package usecase.BuildPokemonTeam;

import entity.Team;

public class BuildPokemonTeamOutputData {
    private final Team team;

    public BuildPokemonTeamOutputData(Team Team) {
        this.team = Team;
    }

    public Team getTeam() {
        return team;
    }

}
