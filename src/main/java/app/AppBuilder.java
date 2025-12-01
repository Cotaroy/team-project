package app;

import dataaccess.BuildPokemonTeamDataAccessObject;
import dataaccess.PokemonLookupDataAccessObject;
import entity.EmptyPokemonFactory;
import entity.TeamGrader;
import interface_adapter.ViewManagerModel;
import interface_adapter.pokemonlookup.PokemonLookupController;
import interface_adapter.pokemonlookup.PokemonLookupPresenter;
import interface_adapter.pokemonlookup.PokemonLookupViewModel;
import interface_adapter.teambuilder.TeamBuilderController;
import interface_adapter.teambuilder.TeamBuilderPresenter;
import interface_adapter.teambuilder.TeamBuilderViewModel;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamInteractor;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInteractor;
import usecase.lookup.PokemonLookupOutputBoundary;
import usecase.grade_team.GradeTeamInteractor;
import view.PokemonLookupView;
import view.TeamBuilderView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final BuildPokemonTeamDataAccessObject buildPokemonTeamDataAccessObject = new BuildPokemonTeamDataAccessObject();
    private final PokemonLookupDataAccessObject pokemonLookupDataAccessObject = new PokemonLookupDataAccessObject();

    private PokemonLookupView pokemonLookupView;
    private PokemonLookupViewModel pokemonLookupViewModel;

    private TeamBuilderView teamBuilderView;
    private TeamBuilderViewModel teamBuilderViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addPokemonLookupView() {
        pokemonLookupViewModel = new PokemonLookupViewModel();
        pokemonLookupView = new PokemonLookupView(pokemonLookupViewModel);
        cardPanel.add(pokemonLookupView, pokemonLookupView.getViewName());
        return this;
    }

    public AppBuilder addPokemonLookupUseCase() {
        final PokemonLookupOutputBoundary pokemonLookupOutputBoundary = new PokemonLookupPresenter(
                pokemonLookupViewModel, teamBuilderViewModel, viewManagerModel);
        final PokemonLookupInputBoundary pokemonLookupInteractor =
                new PokemonLookupInteractor(pokemonLookupOutputBoundary, EmptyPokemonFactory.create(), pokemonLookupDataAccessObject);
        PokemonLookupController controller = new PokemonLookupController(pokemonLookupInteractor);
        pokemonLookupView.setPokemonLookupController(controller);
        return this;
    }

    public AppBuilder addTeamBuilderView() {
        teamBuilderViewModel = new TeamBuilderViewModel();
        teamBuilderView = new TeamBuilderView(teamBuilderViewModel);
        cardPanel.add(teamBuilderView, teamBuilderView.getViewName());
        return this;
    }

    public AppBuilder addTeamBuilderUseCase() {
        final TeamBuilderPresenter buildPokemonTeamOutputBoundary = new TeamBuilderPresenter(
                teamBuilderViewModel, pokemonLookupViewModel, viewManagerModel);
        final BuildPokemonTeamInputBoundary buildPokemonTeamInteractor =
                new BuildPokemonTeamInteractor(buildPokemonTeamDataAccessObject, buildPokemonTeamOutputBoundary, EmptyPokemonFactory.create());
        TeamBuilderController controller = new TeamBuilderController(new TeamGrader(), buildPokemonTeamInteractor);

        controller.setUserGradeTeamUseCaseInteractor(new GradeTeamInteractor(teamBuilderViewModel.getState(), buildPokemonTeamOutputBoundary));

        teamBuilderView.setTeamBuilderController(controller);
        teamBuilderView.setSavedTeamsDropdown();
        return this;
    }

    public JFrame build() {
        JFrame application = new JFrame("MetaGross");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(teamBuilderView.getViewName());

        return application;
    }
}
