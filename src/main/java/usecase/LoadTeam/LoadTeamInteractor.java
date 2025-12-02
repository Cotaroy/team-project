package usecase.LoadTeam;

import entity.Team;

import java.io.IOException;

public class LoadTeamInteractor implements LoadTeamInputBoundary {
    private final LoadTeamDataAccessInterface dataAccess;
    private final LoadTeamOutputBoundary presenter;

    public LoadTeamInteractor(LoadTeamDataAccessInterface dataAccess, LoadTeamOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }
    @Override
    public void execute(LoadTeamInputData inputData) throws IOException {
        String teamName = inputData.getTeamName();
        if ("".equals(teamName)) {
            presenter.prepareFailView("No Pokemon name provided.");
        }
        else {
                Team team = dataAccess.loadTeam(teamName);
                if (team == null) {
                    presenter.prepareFailView("Team not found.");
                } else {
                    final LoadTeamOutputData outputData = new LoadTeamOutputData(team);
                    presenter.prepareSuccessView(outputData);
                }
            }
        }
    }
