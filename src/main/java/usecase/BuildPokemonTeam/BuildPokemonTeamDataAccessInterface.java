package usecase.BuildPokemonTeam;

import java.io.FileNotFoundException;

import entity.Team;

public interface BuildPokemonTeamDataAccessInterface {
    /**
     * Saves or updates a team.
     * @param team the team to save
     */
    void saveTeam(Team team);

    /**
     * Checks if a team exists.
     * @param name name of the team
     * @return true if team exists.
     * @throws FileNotFoundException if ever.
     */
    boolean teamExists(Team name) throws FileNotFoundException;

}
