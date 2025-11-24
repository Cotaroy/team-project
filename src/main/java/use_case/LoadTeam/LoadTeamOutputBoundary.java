package use_case.LoadTeam;

public interface LoadTeamOutputBoundary {
    void prepareSuccessView(LoadTeamOutputData outputData);
    void prepareFailureView(String errorMessage);
}
