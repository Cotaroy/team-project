package usecase.LoadTeam;

import java.io.IOException;

import entity.Team;

public class LoadTeamInteractor implements LoadTeamInputBoundary {
    /**
     * The data access object.
     */
    private final LoadTeamDataAccessInterface dataAccess;
    /**
     * The presenter object.
     */
    private final LoadTeamOutputBoundary presenter;

    /**
     * Initalize the load team interactor.
     *
     * @param dataAccessParam the data access object.
     * @param presenterParam  the presenter object.
     */
    public LoadTeamInteractor(final LoadTeamDataAccessInterface dataAccessParam,
                              final LoadTeamOutputBoundary presenterParam) {
        this.dataAccess = dataAccessParam;
        this.presenter = presenterParam;
    }

    /**
     * Execute the load team use case.
     *
     * @param inputData name of the team.
     * @throws IOException exception.
     */
    @Override
    public void execute(final LoadTeamInputData inputData) throws IOException {
        final String teamName = inputData.getTeamName();
        if ("".equals(teamName)) {
            presenter.prepareFailView("No Pokemon name provided.");
        }
        else {
            final Team team = dataAccess.loadTeam(teamName);
            if (team == null) {
                presenter.prepareFailView("Team not found.");
            }
            else {
                final LoadTeamOutputData outputData =
                        new LoadTeamOutputData(team);
                presenter.prepareSuccessView(outputData);
            }
        }
    }
}
