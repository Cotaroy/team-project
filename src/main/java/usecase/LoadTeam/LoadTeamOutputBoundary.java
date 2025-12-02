package usecase.LoadTeam;

public interface LoadTeamOutputBoundary {
    /**
     * Display the team that was retrieved.
     * @param outputData Team that was retrieved
     */
    void prepareSuccessView(LoadTeamOutputData outputData);

    /**
     * Prepare the fail view.
     * @param errorMessage error message
     */
    void prepareFailView(String errorMessage);
}
