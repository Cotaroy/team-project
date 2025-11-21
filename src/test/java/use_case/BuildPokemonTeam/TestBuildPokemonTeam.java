package use_case.BuildPokemonTeam;

import data_access.InMemoryUserDataAccessObject;
import entity.Pokemon;
import entity.EmptyPokemonFactory;
import entity.Team;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestBuildPokemonTeam {

    @Test
    public void BuildPokemonTeamTestWithIndex() throws IOException {
        Team t = new Team("Chuckle Sandwich");
        EmptyPokemonFactory x = new EmptyPokemonFactory();
        Pokemon a = x.create();

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
        };

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.addToTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestWithoutIndexFullTeam() throws IOException {
        Team t = new Team("Did Schlatt Win?");
        EmptyPokemonFactory x = new EmptyPokemonFactory();
        Pokemon a = x.create();
        Pokemon b = x.create();
        Pokemon c = x.create();
        Pokemon d = x.create();
        Pokemon e = x.create();
        Pokemon f = x.create();
        Pokemon z = x.create();
        t.setPokemon(a, 0);
        t.setPokemon(b, 1);
        t.setPokemon(c, 2);
        t.setPokemon(d, 3);
        t.setPokemon(e, 4);
        t.setPokemon(f, 5);

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
        };

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, presenter, z);
        interactor.addToTeam(inputData);
    }

    @Test
    public void BuildPokemonTeamTestRemovePokemon() throws IOException { //THIS REMOVES A POKEMON AT INDEX 0
        Team t = new Team("Let's Not Meet");
        EmptyPokemonFactory x = new EmptyPokemonFactory();
        Pokemon a = x.create();
        Pokemon b = x.create();
        Pokemon c = x.create();
        Pokemon d = x.create();
        Pokemon e = x.create();
        Pokemon f = x.create();
        t.setPokemon(a, 0);
        t.setPokemon(b, 1);
        t.setPokemon(c, 2);
        t.setPokemon(d, 3);
        t.setPokemon(e, 4);
        t.setPokemon(f, 5);

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

        };

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(userDataAccessObject, successPresenter, a);
        interactor.removeFromTeam(inputData);

    }


}


