package usecase.BuildPokemonTeam;
import entity.Team;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public interface BuildPokemonTeamDataAccessInterface {
    /**
     * Saves a team.
     * @param team the team to save
     */
    void saveTeam(Team team) throws TeamExistsException;

    /**
     * Overwrite a saved team.
     * @param team the team being overwritten
     */
    void overwriteTeam(Team team);

    /**
     * Checks if a team exists.
     * @param name name of the team
     * @return true if team exists
     */
    boolean teamExists(Team name) throws FileNotFoundException;

    class TeamExistsException extends Exception {
        public TeamExistsException(String teamName) {
            super(teamName + " already exists");
        }
    }
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
