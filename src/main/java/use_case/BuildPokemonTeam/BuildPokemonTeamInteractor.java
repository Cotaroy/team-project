package use_case.BuildPokemonTeam;
import data_access.BuildPokemonTeamDataAccessObject;
import entity.Team;

import entity.Pokemon;

import java.io.IOException;

public class BuildPokemonTeamInteractor implements BuildPokemonTeamInputBoundary {
    private final BuildPokemonTeamDataAccessInterface userDataAccessObject;
    private final BuildPokemonTeamOutputBoundary userPresenter;
    private final Pokemon Pokemon;


    public BuildPokemonTeamInteractor(BuildPokemonTeamDataAccessInterface buildPokemonTeamDataAccessInterface,
                                      BuildPokemonTeamOutputBoundary buildPokemonTeamOutputBoundary,
                                      Pokemon Pokemon) {
        this.userDataAccessObject = buildPokemonTeamDataAccessInterface;
        this.userPresenter = buildPokemonTeamOutputBoundary;
        this.Pokemon = Pokemon;
    }


    @Override
    public void addToTeam(BuildPokemonTeamInputData buildPokemonTeamInputData) throws IOException {

        final int index = buildPokemonTeamInputData.getIndex();
        final Team team = buildPokemonTeamInputData.getTeam();


        // Checks to see if the team can even be added to.
        if (index == -1){
            // Goes through each team slot to see if there's a slot that can be added to.
            for ( int i = 0; i < 6; i++) {
                if (team.getPokemon(i) == null){
                    team.setPokemon(Pokemon, i);
                    final BuildPokemonTeamOutputData buildPokemonTeamOutputData =
                            new BuildPokemonTeamOutputData(team);
                    userPresenter.prepareSuccessView(buildPokemonTeamOutputData);
                    return;
                }
            }
            userPresenter.prepareFailView("Team is full. Please remove a Pokemon or create a new Team.");
            return;
        }

        team.setPokemon(Pokemon, index);

        final BuildPokemonTeamOutputData buildPokemonTeamOutputData =
                new BuildPokemonTeamOutputData(team);
        userPresenter.prepareSuccessView(buildPokemonTeamOutputData);

    }

    @Override
    public void removeFromTeam(BuildPokemonTeamInputData buildPokemonTeamInputData) throws IOException {

        final int index = buildPokemonTeamInputData.getIndex();
        final Team team = buildPokemonTeamInputData.getTeam();
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
}
