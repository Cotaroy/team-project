package usecase.LoadTeam;

public interface LoadTeamOutputBoundary {
    void prepareSuccessView(LoadTeamOutputData outputData);
    void prepareFailureView(String errorMessage);
}
