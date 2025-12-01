package interface_adapter.teambuilder;

import entity.GradingStrategy;
import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputData;
import usecase.grade_team.GradeTeamInputBoundary;
import usecase.grade_team.GradeTeamInputData;

import java.io.IOException;
import java.util.ArrayList;

public class TeamBuilderController {

    private final GradingStrategy gradingStrategy;

    private final BuildPokemonTeamInputBoundary userTeamBuilderUseCaseInteractor;

    private GradeTeamInputBoundary userGradeTeamUseCaseInteractor;

    public TeamBuilderController(GradingStrategy gradingStrategy, BuildPokemonTeamInputBoundary userTeamBuilderUseCaseInteractor) {
        this.gradingStrategy = gradingStrategy;
        this.userTeamBuilderUseCaseInteractor = userTeamBuilderUseCaseInteractor;
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

    public void saveTeam(Team team) throws IOException {
        final BuildPokemonTeamInputData buildPokemonTeamInputData = new BuildPokemonTeamInputData(team.getTeamName(), team);
        userTeamBuilderUseCaseInteractor.saveTeam(buildPokemonTeamInputData);
    }

    public Team loadTeam(String teamName) throws IOException {
        return this.userTeamBuilderUseCaseInteractor.loadTeam(teamName);
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
