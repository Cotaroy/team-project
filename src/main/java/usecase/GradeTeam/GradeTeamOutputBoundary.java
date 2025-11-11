package usecase.GradeTeam;

public interface GradeTeamOutputBoundary {

    /**
     * Prepares the success view for Grade Team.
     * @param gradeTeamOutputData the output data
     */
    void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData);

    /**
     * Prepares the failure view for Grade Team.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailureView(String errorMessage);
}
