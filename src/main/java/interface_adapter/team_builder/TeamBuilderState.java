package interface_adapter.team_builder;

import entity.Team;
import use_case.grade_team.GradeTeamUserDataAccessInterface;

public class TeamBuilderState implements GradeTeamUserDataAccessInterface {
    private String teamNameError;

    private Team team = new Team("Team 1");

    private float teamScore = 0;

    public String getTeamNameError() {
        return teamNameError;
    }

    public Team getTeam() {
        return team;
    }

    public float getTeamScore() {
        return teamScore;
    }

    public void setTeamNameError(String teamNameError) {
        this.teamNameError = teamNameError;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeamScore(float teamScore) {
        this.teamScore = teamScore;
    }

    @Override
    public Team getTeam(String teamName) {
        return getTeam();
    }
}
