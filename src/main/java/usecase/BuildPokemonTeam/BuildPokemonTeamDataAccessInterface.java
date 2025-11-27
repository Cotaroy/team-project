package usecase.BuildPokemonTeam;
import entity.Team;
import java.io.FileNotFoundException;


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
