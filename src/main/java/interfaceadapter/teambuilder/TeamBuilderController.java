package interfaceadapter.teambuilder;

import java.io.IOException;
import java.util.ArrayList;

import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputData;
import usecase.LoadTeam.LoadTeamInputBoundary;
import usecase.LoadTeam.LoadTeamInputData;
import usecase.grade_team.GradeTeamInputBoundary;
import usecase.grade_team.GradeTeamInputData;
import usecase.grade_team.GradingStrategy;

public class TeamBuilderController {

    private final GradingStrategy gradingStrategy;

    private final BuildPokemonTeamInputBoundary userTeamBuilderUseCaseInteractor;

    private GradeTeamInputBoundary userGradeTeamUseCaseInteractor;

    private LoadTeamInputBoundary loadTeamInteractor;

    public TeamBuilderController(GradingStrategy gradingStrategy,
                                 BuildPokemonTeamInputBoundary userTeamBuilderUseCaseInteractor,
                                 LoadTeamInputBoundary loadTeamInteractor) {
        this.gradingStrategy = gradingStrategy;
        this.userTeamBuilderUseCaseInteractor = userTeamBuilderUseCaseInteractor;
        this.loadTeamInteractor = loadTeamInteractor;
    }

    public void setUserGradeTeamUseCaseInteractor(GradeTeamInputBoundary userGradeTeamUseCaseInteractor) {
        this.userGradeTeamUseCaseInteractor = userGradeTeamUseCaseInteractor;
    }

    public void addToTeam(String name, Team team, int index) throws IOException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(name, team, index);

        userTeamBuilderUseCaseInteractor.addToTeam(buildPokemonTeamInputData);
    }

    public void addToTeam(String name, Team team) throws IOException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(name, team);

        userTeamBuilderUseCaseInteractor.addToTeam(buildPokemonTeamInputData);
    }

    public void removeFromTeam(String name, Team team, int index) throws IOException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(name, team, index);
        userTeamBuilderUseCaseInteractor.removeFromTeam(buildPokemonTeamInputData);
    }

    public void saveTeam(Team team) throws IOException, BuildPokemonTeamDataAccessInterface.TeamExistsException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(team.getTeamName(), team);
        userTeamBuilderUseCaseInteractor.saveTeam(buildPokemonTeamInputData);
    }

    public void overwriteTeam(Team team) throws IOException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(team.getTeamName(), team);
        userTeamBuilderUseCaseInteractor.overwriteTeam(buildPokemonTeamInputData);
    }

    public void loadTeam(String teamName) {
        final LoadTeamInputData loadTeamInputData = new LoadTeamInputData(teamName);
        loadTeamInteractor.execute(loadTeamInputData);
    }

    public ArrayList<String> getAllTeamNames(){
        return userTeamBuilderUseCaseInteractor.getAllTeamNames();
    }

    public void gradeTeam(Team team) {
        final GradeTeamInputData gradeTeamInputData = new GradeTeamInputData(team.getTeamName(), gradingStrategy);
        userGradeTeamUseCaseInteractor.execute(gradeTeamInputData);
    }

    public void switchToPokemonLookupView(int index) {
        userTeamBuilderUseCaseInteractor.switchToPokemonLookupView(index);
    }


}
