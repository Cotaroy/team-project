package usecase.LoadTeam;

import entity.Team;

public class LoadTeamInteractor implements LoadTeamInputBoundary {
    private final LoadTeamDataAccessInterface dataAccess;
    private final LoadTeamOutputBoundary presenter;

    public LoadTeamInteractor(LoadTeamDataAccessInterface dataAccess, LoadTeamOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }
    @Override
    public void execute(LoadTeamInputData inputData) {
        String teamName = inputData.getTeamName();
        if ("".equals(teamName)) {
            presenter.prepareFailureView("No Pokemon name provided.");
        }
        else {
            try {
                Team team = dataAccess.loadTeam(teamName);
                if (team == null) {
                    presenter.prepareFailureView("Team not found.");
                } else {
                    final LoadTeamOutputData outputData = new LoadTeamOutputData(team);
                    presenter.prepareSuccessView(outputData);
                }
            }
            catch (Exception e) {
                presenter.prepareFailureView("Some exception occurred");
            }
        }
    }
}
