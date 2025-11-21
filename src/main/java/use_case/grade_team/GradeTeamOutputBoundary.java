package use_case.grade_team;

public interface GradeTeamOutputBoundary {
    void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData);
    void prepareFailView(String errorMessage);
}
