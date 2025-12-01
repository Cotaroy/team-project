package usecase.grade_team;

import entity.Team;

public interface GradeTeamUserDataAccessInterface {
    Team getTeam(String teamName);
}
