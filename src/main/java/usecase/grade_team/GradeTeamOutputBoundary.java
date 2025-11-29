package usecase.grade_team;

public interface GradeTeamOutputBoundary {

    /**
     * Called when GradeTeam use case is successful.
     * @param gradeTeamOutputData the output data the use case returns
     */
    void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData);
}
