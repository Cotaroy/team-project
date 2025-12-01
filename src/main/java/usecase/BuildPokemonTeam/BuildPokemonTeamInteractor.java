package usecase.BuildPokemonTeam;

import java.io.IOException;
import java.util.ArrayList;

import entity.Pokemon;
import entity.Team;

public class BuildPokemonTeamInteractor implements BuildPokemonTeamInputBoundary {
    private final BuildPokemonTeamDataAccessInterface userDataAccessObject;
    private final BuildPokemonTeamOutputBoundary userPresenter;
    private final Pokemon pokemon;

    public BuildPokemonTeamInteractor(BuildPokemonTeamDataAccessInterface buildPokemonTeamDataAccessInterface,
                                      BuildPokemonTeamOutputBoundary buildPokemonTeamOutputBoundary,
                                      Pokemon pokemon) {
        this.userDataAccessObject = buildPokemonTeamDataAccessInterface;
        this.userPresenter = buildPokemonTeamOutputBoundary;
        this.pokemon = pokemon;
    }

    @Override
    public void addToTeam(BuildPokemonTeamInputData buildPokemonTeamInputData) throws IOException {

        final int index = buildPokemonTeamInputData.getIndex();
        final Team team = buildPokemonTeamInputData.getSelectedTeam();

        // Checks to see if the team can even be added to.
        if (index == -1) {
            // Goes through each team slot to see if there's a slot that can be added to.
            for (int i = 0; i < Team.TOTAL_TEAM_SLOTS; i++) {
                if (team.getPokemon(i) == null) {
                    team.setPokemon(pokemon, i);
                    final BuildPokemonTeamOutputData buildPokemonTeamOutputData =
                            new BuildPokemonTeamOutputData(team);
                    userPresenter.prepareSuccessView(buildPokemonTeamOutputData);
                    return;
                }
            }
            userPresenter.prepareFailView("Team is full. Please remove a Pokemon or create a new Team.");
            return;
        }

        team.setPokemon(pokemon, index);

        final BuildPokemonTeamOutputData buildPokemonTeamOutputData =
                new BuildPokemonTeamOutputData(team);
        userPresenter.prepareSuccessView(buildPokemonTeamOutputData);

    }

    @Override
    public void removeFromTeam(BuildPokemonTeamInputData buildPokemonTeamInputData) throws IOException {

        final int index = buildPokemonTeamInputData.getIndex();
        final Team team = buildPokemonTeamInputData.getSelectedTeam();
        team.setPokemon(null, index);

        final BuildPokemonTeamOutputData buildPokemonTeamOutputData =
                new BuildPokemonTeamOutputData(team);
        userPresenter.prepareSuccessView(buildPokemonTeamOutputData);

    }

    @Override
    public void saveTeam(BuildPokemonTeamInputData buildPokemonTeamInputData) throws IOException {
        final Team team = buildPokemonTeamInputData.getTeam();
        userDataAccessObject.saveTeam(team);
    }

    @Override
    public void switchToPokemonLookupView(int index) {
        userPresenter.switchToPokemonLookupView(index);
    }

    @Override
    public ArrayList<String> getAllTeamNames(){
        return userDataAccessObject.getAllTeamNames();
    }

    @Override
    public Team loadTeam(String teamName) throws IOException{
        return userDataAccessObject.loadTeam(teamName);
    }
}
