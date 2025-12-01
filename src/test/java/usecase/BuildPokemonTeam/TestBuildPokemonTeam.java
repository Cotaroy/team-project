package usecase.BuildPokemonTeam;

import dataaccess.InMemoryUserDataAccessObject;
import dataaccess.PokemonMap;
import entity.Pokemon;
import entity.EmptyPokemonFactory;
import entity.Team;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestBuildPokemonTeam {

    @Test
    public void overwriteExistingTeamTest() throws IOException {
        Team team = new Team("I think, therefore I am");
        Pokemon magikarp = new PokemonMap().getPokemon("magikarp");
        team.setPokemon(magikarp, 0);
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        try {
            userDataAccessObject.saveTeam(team);
        } catch (BuildPokemonTeamDataAccessInterface.TeamExistsException e) {
            throw new RuntimeException(e);
        }

        team.setPokemon(EmptyPokemonFactory.create(), 0);

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(magikarp.getName(), team);
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertEquals(team, outputData.getTeam());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToPokemonLookupView(int index) {

            }
        };
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter,
                EmptyPokemonFactory.create());
        try {
            interactor.saveTeam(inputData);
        } catch (BuildPokemonTeamDataAccessInterface.TeamExistsException e) {
            interactor.overwriteTeam(inputData);
        }

    }

    @Test
    public void BuildPokemonTeamTestWithIndex() throws IOException {
        Team t = new Team("Chuckle Sandwich");
        EmptyPokemonFactory x = new EmptyPokemonFactory();
        Pokemon a = EmptyPokemonFactory.create();

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(a.getName(), t, 0);
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertEquals(t, outputData.getTeam());
                assertEquals(a, t.getPokemon(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToPokemonLookupView(int index) {
            }

        };

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.addToTeam(inputData);

    }

    @Test
    public void BuildPokemonTeamTestWithoutIndex() throws IOException {
        Team t = new Team("Critical Roll");
        EmptyPokemonFactory x = new EmptyPokemonFactory();
        Pokemon a = x.create();

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(a.getName(), t); //HAS NO INDEX
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertEquals(t, outputData.getTeam());
                assertEquals(inputData.getName(), a.getName()); //checks if the right Pokémon name is used in the interactor.
                assertEquals(a, t.getPokemon(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);

            }

            @Override
            public void switchToPokemonLookupView(int index) {

            }
        };

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.addToTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestWithoutIndexFullTeam() throws IOException {
        Team t = new Team("Did Schlatt Win?");
        for (int i = 0; i < 6; i++){
            Pokemon a = EmptyPokemonFactory.create();
            t.setPokemon(a, i);
        }
        Pokemon z = EmptyPokemonFactory.create();

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(z.getName(), t); //HAS NO INDEX
        BuildPokemonTeamOutputBoundary presenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                fail("Should not be called");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Team is full. Please remove a Pokemon or create a new Team.", errorMessage);
            }

            @Override
            public void switchToPokemonLookupView(int index) {

            }
        };
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, presenter, z);
        interactor.addToTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestRemovePokemon() throws IOException { //THIS REMOVES A POKEMON AT INDEX 0
        Team t = new Team("Let's Not Meet");
        Pokemon a = EmptyPokemonFactory.create();
        for (int i = 1; i < 5; i++){
            Pokemon b = EmptyPokemonFactory.create();
            t.setPokemon(b, i);
        }

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(a.getName(), t, 0);
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertNull(t.getPokemon(0));
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("The Pokemon was not removed properly!");
            }
            @Override
            public void switchToPokemonLookupView(int index) {
            }
        };
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.removeFromTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestSave() throws IOException { //Checks if save team is used.
        Team t = new Team("Let's Not Meet");
        Pokemon a = EmptyPokemonFactory.create();
        for (int i = 1; i < 5; i++){
            Pokemon b = EmptyPokemonFactory.create();
            t.setPokemon(b, i);
        }

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(a.getName(), t, 0);
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertNotNull(t.getPokemon(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The Pokemon was not saved properly!");
            }

            @Override
            public void switchToPokemonLookupView(int index) {
            }
        };
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.saveTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestPresenter() throws IOException { //Checks if presenter is called.
        Team t = new Team("Let's Not Meet");
        Pokemon a = EmptyPokemonFactory.create();
        for (int i = 1; i < 5; i++){
            Pokemon b = EmptyPokemonFactory.create();
            t.setPokemon(b, i);
        }

        BuildPokemonTeamInputData inputData = new BuildPokemonTeamInputData(a.getName(), t, 0);
        BuildPokemonTeamOutputBoundary successPresenter = new BuildPokemonTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(BuildPokemonTeamOutputData outputData) {
                assertNotNull(t.getPokemon(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The Pokemon was not saved properly!");
            }

            @Override
            public void switchToPokemonLookupView(int index) {
            }
        };
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.switchToPokemonLookupView(inputData.getIndex());
    }

}

