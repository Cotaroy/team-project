package usecase.grade_team;

import entity.Team;

public interface GradeTeamUserDataAccessInterface {
    /**
     * Search for a team.
     * @param teamName name of the Team
     * @return Team object with name teamName
     */
    Team getTeam(String teamName);
}
