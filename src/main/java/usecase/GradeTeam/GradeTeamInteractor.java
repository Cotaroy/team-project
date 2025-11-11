package usecase.GradeTeam;

public class GradeTeamInteractor implements GradeTeamInputBoundary {

    private final GradeTeamUserDataAccessInterface userDataAccessObject;
    private final GradeTeamOutputBoundary userPresenter;

    public GradeTeamInteractor(GradeTeamUserDataAccessInterface userDataAccessObject,
                               GradeTeamOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(GradeTeamInputData gradeTeamInputData) {
        try {
            final float teamScore = gradeTeamInputData.getStrategy()
                    .execute(userDataAccessObject.getTeam(gradeTeamInputData.getTeamName()));
            final GradeTeamOutputData gradeTeamOutputData = new GradeTeamOutputData(teamScore);
            userPresenter.prepareSuccessView(gradeTeamOutputData);
        }
        catch (IndexOutOfBoundsException exception) {
            userPresenter.prepareFailureView("Index out of bounds exception.");
        }
    }
}
