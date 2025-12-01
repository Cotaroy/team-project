package usecase.grade_team;

public class GradeTeamInteractor implements GradeTeamInputBoundary {

    private final GradeTeamUserDataAccessInterface userDataAccessObject;
    private final GradeTeamOutputBoundary userPresenter;

    public GradeTeamInteractor(GradeTeamUserDataAccessInterface userDataAccessObject, GradeTeamOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(GradeTeamInputData gradeTeamInputData) {
        try{
            float teamScore = gradeTeamInputData.getStrategy().execute(userDataAccessObject.getTeam(gradeTeamInputData.getTeamName()));
            GradeTeamOutputData gradeTeamOutputData = new GradeTeamOutputData(teamScore);
            userPresenter.prepareSuccessView(gradeTeamOutputData);
        }catch(Exception e){
            userPresenter.prepareFailView("Some exception occurred");
        }
    }
}
