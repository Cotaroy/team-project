package usecase.LoadTeam;

import java.io.IOException;

public interface LoadTeamInputBoundary {
    /**
     * Interface for input boundary of load team use case.
     * @param loadTeamInputData name of the team.
     * @throws IOException exception
     */
    void execute(LoadTeamInputData loadTeamInputData) throws IOException;
}
