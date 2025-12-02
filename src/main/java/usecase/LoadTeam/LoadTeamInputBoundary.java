package usecase.LoadTeam;

import java.io.IOException;

public interface LoadTeamInputBoundary {
    void execute(LoadTeamInputData loadTeamInputData) throws IOException;
}
