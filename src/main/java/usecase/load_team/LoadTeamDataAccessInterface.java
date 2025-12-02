package usecase.load_team;

import java.io.IOException;

import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

public interface LoadTeamDataAccessInterface {
    /**
     * Interface for load data access object.
     * @param teamName Name of team being loaded.
     * @return Loaded team object.
     * @throws IOException exception.
     */
    Team loadTeam(String teamName) throws IOException;

    /**
     * Saves a team.
     * @param team the team to save
     * @throws BuildPokemonTeamDataAccessInterface.TeamExistsException exception.
     */
    void saveTeam(Team team) throws
            BuildPokemonTeamDataAccessInterface.TeamExistsException;
}
