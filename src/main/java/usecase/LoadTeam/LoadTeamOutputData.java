package usecase.LoadTeam;

import entity.Team;

public class LoadTeamOutputData {
    private final Team team;

    public LoadTeamOutputData(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;
    }
}
