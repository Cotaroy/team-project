package usecase.LoadTeam;

import java.io.IOException;

import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

public interface LoadTeamDataAccessInterface {
    Team loadTeam(String teamName) throws IOException;
    void saveTeam(Team team) throws
            BuildPokemonTeamDataAccessInterface.TeamExistsException;
}
