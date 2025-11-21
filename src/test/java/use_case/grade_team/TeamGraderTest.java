package use_case.grade_team;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TeamGraderTest {

    @Test
    void totalStat399Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 399)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(5, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat400Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 400)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(6, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat450Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 450)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(7, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat500Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 500)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(8, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat550Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 550)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(9, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat600Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 600)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(10, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void totalStat601Test(){
        Team team = new Team("statTest");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setStats(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 601)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("statTest", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(10, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void twoTypesCoverageTest(){
        Team team = new Team("twoTypesOnePokemonTeam");
        Type normalType = new Type("normal", 1,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(Arrays.asList("ghost", "rock", "steel")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/1.png");
        Type fightingType = new Type("fighting", 2,
                new HashSet<>(Arrays.asList("normal", "rock", "steel", "ice", "dark")),
                new HashSet<>(Arrays.asList("flying", "psychic", "fairy")),
                new HashSet<>(Arrays.asList("bug", "rock", "dark")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/2.png");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setType1(normalType);
        pokemon.setType2(fightingType);
        pokemon.setStats(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("twoTypesOnePokemonTeam", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(15.0, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void threeTypesCoverageTest(){
        Team team = new Team("threeTypesTwoPokemonTeam");
        Type normalType = new Type("normal", 1,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(Arrays.asList("ghost", "rock", "steel")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/1.png");
        Type fightingType = new Type("fighting", 2,
                new HashSet<>(Arrays.asList("normal", "rock", "steel", "ice", "dark")),
                new HashSet<>(Arrays.asList("flying", "psychic", "fairy")),
                new HashSet<>(Arrays.asList("bug", "rock", "dark")),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/2.png");

        HashSet<String> waterstrength = new HashSet<>(Arrays.asList("fire", "rock", "ground"));
        HashSet<String> waterweak = new HashSet<>(Arrays.asList("grass", "electric"));
        HashSet<String> wateres = new HashSet<>(Arrays.asList("steel", "fire", "ice", "water"));

        Type waterType = new Type("water", 11,
                waterstrength, waterweak, wateres, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/11.png");

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setType1(normalType);
        pokemon.setType2(fightingType);
        pokemon.setStats(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));

        Pokemon pokemon2 = EmptyPokemonFactory.create();
        pokemon2.setType1(waterType);
        pokemon2.setStats(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));

        team.setPokemon(pokemon, 0);
        team.setPokemon(pokemon2, 1);

        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("threeTypesTwoPokemonTeam", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(25.0, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
