package interfaceadapter.teambuilder;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.pokemonlookup.PokemonLookupState;
import interfaceadapter.pokemonlookup.PokemonLookupViewModel;
import usecase.BuildPokemonTeam.BuildPokemonTeamOutputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamOutputData;
import usecase.grade_team.GradeTeamOutputBoundary;
import usecase.grade_team.GradeTeamOutputData;

public class TeamBuilderPresenter implements BuildPokemonTeamOutputBoundary, GradeTeamOutputBoundary {

    private final TeamBuilderViewModel teamBuilderViewModel;
    private final PokemonLookupViewModel pokemonLookupViewModel;
    private final ViewManagerModel viewManagerModel;

    public TeamBuilderPresenter(TeamBuilderViewModel teamBuilderViewModel,
                                PokemonLookupViewModel pokemonLookupViewModel,
                                ViewManagerModel viewManagerModel) {
        this.teamBuilderViewModel = teamBuilderViewModel;
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
        final TeamBuilderState teamBuilderState = teamBuilderViewModel.getState();
        this.viewManagerModel.setState(pokemonLookupViewModel.getViewName());
        teamBuilderState.setTeam(outputData.getTeam());
        teamBuilderViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final TeamBuilderState teamBuilderState = teamBuilderViewModel.getState();
        teamBuilderState.setTeamNameError(errorMessage);
        teamBuilderViewModel.firePropertyChange();
    }

    @Override
    public void switchToPokemonLookupView(int index) {
        PokemonLookupState state = pokemonLookupViewModel.getState();
        state.setIndex(index);
        viewManagerModel.setState(pokemonLookupViewModel.getViewName());
        viewManagerModel.firePropertyChange();
        pokemonLookupViewModel.firePropertyChange();
    }

    @Override
    public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
        final TeamBuilderState teamBuilderState = teamBuilderViewModel.getState();
        teamBuilderState.setTeamScore(gradeTeamOutputData.getTeamScore());
        teamBuilderViewModel.firePropertyChange();
    }
}
