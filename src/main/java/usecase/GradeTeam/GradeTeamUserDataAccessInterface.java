package usecase.GradeTeam;

import entity.Team;

public interface GradeTeamUserDataAccessInterface {
    /**
     * Retrieves a team from the database.
     * @param teamName name of the team
     * @return the team with the given name
     */
    Team getTeam(String teamName);
}
