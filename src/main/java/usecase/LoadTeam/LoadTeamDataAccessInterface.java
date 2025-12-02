package usecase.LoadTeam;

import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

import java.io.IOException;

public interface LoadTeamDataAccessInterface {
    Team loadTeam(String teamName) throws IOException;

    /**
     * Saves a team.
     * @param team the team to save
     */
    void saveTeam(Team team) throws BuildPokemonTeamDataAccessInterface.TeamExistsException;
}
