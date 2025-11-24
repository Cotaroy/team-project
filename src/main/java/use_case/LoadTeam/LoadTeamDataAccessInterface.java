package use_case.LoadTeam;

import entity.Team;

import java.io.IOException;

public interface LoadTeamDataAccessInterface {
    Team loadTeam(String teamName) throws IOException;
}
