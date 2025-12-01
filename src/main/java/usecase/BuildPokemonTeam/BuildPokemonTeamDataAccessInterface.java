package usecase.BuildPokemonTeam;
import entity.Team;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public interface BuildPokemonTeamDataAccessInterface {
    /**
     * Saves or updates a team.
     * @param team the team to save
     */
    void saveTeam(Team team);

    /**
     * Checks if a team exists.
     * @param name name of the team
     * @return true if team exists
     */
    boolean teamExists(Team name) throws FileNotFoundException;

    /**
     * returns all saved team names
     * @return list of team names
     */
    ArrayList<String> getAllTeamNames();


    /**
     * returns a saved team that's being loaded
     * @param teamName name of the team
     * @return Team the team being loaded
     */
    Team loadTeam(String teamName) throws IOException;

}
