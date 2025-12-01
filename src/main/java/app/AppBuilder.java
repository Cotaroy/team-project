package app;

import dataaccess.BuildPokemonTeamDataAccessObject;
import dataaccess.PokemonLookupDataAccessObject;
import dataaccess.RegionPokedexDataAccess;
import entity.EmptyPokemonFactory;
import usecase.filter.*;
import usecase.grade_team.TeamGrader;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.pokemonlookup.PokemonLookupController;
import interfaceadapter.pokemonlookup.PokemonLookupPresenter;
import interfaceadapter.pokemonlookup.PokemonLookupViewModel;
import interfaceadapter.teambuilder.TeamBuilderController;
import interfaceadapter.teambuilder.TeamBuilderPresenter;
import interfaceadapter.teambuilder.TeamBuilderViewModel;
import interfaceadapter.main_menu.MainMenuController;
import interfaceadapter.main_menu.MainMenuPresenter;
import interfaceadapter.main_menu.MainViewModel;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamInteractor;
import usecase.lookup.PokemonLookupInputBoundary;
import usecase.lookup.PokemonLookupInteractor;
import usecase.lookup.PokemonLookupOutputBoundary;
import usecase.grade_team.GradeTeamInteractor;
import usecase.main_menu.MainMenuInputBoundary;
import usecase.main_menu.MainMenuInteractor;
import usecase.main_menu.MainMenuOutputBoundary;
import usecase.seeRegionPokedex.RegionPokedexInputBoundary;
import usecase.seeRegionPokedex.RegionPokedexInteractor;
import usecase.seeRegionPokedex.RegionPokedexOutputBoundary;
import view.HomePageView;
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
    private final FilterPokemonDataAccess filterPokemonDataAccessObject = new FilterPokemonDataAccess();
    private final RegionPokedexDataAccess regionPokedexDataAccess = new RegionPokedexDataAccess();

    private PokemonLookupView pokemonLookupView;
    private PokemonLookupViewModel pokemonLookupViewModel;

    private TeamBuilderView teamBuilderView;
    private TeamBuilderViewModel teamBuilderViewModel;

    private HomePageView homepageView;
    private MainViewModel mainViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addHomePageView() {
        mainViewModel = new MainViewModel();
        homepageView = new HomePageView(mainViewModel);
        cardPanel.add(homepageView, homepageView.getViewName());
        return this;
    }
    public AppBuilder addMainMenuUseCase() {
        MainMenuOutputBoundary mainMenuOutputBoundary = new MainMenuPresenter(mainViewModel, viewManagerModel, teamBuilderViewModel);
        MainMenuInputBoundary mainMenuInputBoundary = new MainMenuInteractor(mainMenuOutputBoundary);
        MainMenuController mainMenuController = new MainMenuController(mainMenuInputBoundary);
        homepageView.setController(mainMenuController);
        return this;
    }
    public AppBuilder addPokemonLookupView() {
        pokemonLookupViewModel = new PokemonLookupViewModel();
        pokemonLookupView = new PokemonLookupView(pokemonLookupViewModel);
        JScrollPane scrollerPokemonLookupView = new JScrollPane(pokemonLookupView);
        cardPanel.add(scrollerPokemonLookupView, pokemonLookupView.getViewName());
        return this;
    }

    public AppBuilder addPokemonLookupUseCase() {
        final PokemonLookupOutputBoundary pokemonLookupOutputBoundary = new PokemonLookupPresenter(
                pokemonLookupViewModel, teamBuilderViewModel, viewManagerModel);
        final PokemonLookupInputBoundary pokemonLookupInteractor =
                new PokemonLookupInteractor(pokemonLookupOutputBoundary, EmptyPokemonFactory.create(), pokemonLookupDataAccessObject);

        final FilterPokemonInputBoundary filterPokemonInteractor =
                new FilterPokemonInteractor(filterPokemonDataAccessObject, (FilterPokemonOutputBoundary) pokemonLookupOutputBoundary);

        final RegionPokedexInputBoundary regionPokedexInteractor =
                new RegionPokedexInteractor(regionPokedexDataAccess, (RegionPokedexOutputBoundary) pokemonLookupOutputBoundary);

        PokemonLookupController controller = new PokemonLookupController(pokemonLookupInteractor,
                filterPokemonInteractor, regionPokedexInteractor);

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
        return this;
    }

    public JFrame build() {
        JFrame application = new JFrame("MetaGross");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homepageView.getViewName());

        return application;
    }
}
