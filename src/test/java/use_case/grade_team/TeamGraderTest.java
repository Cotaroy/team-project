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
    void onePokemonTest(){
        Team team = new Team("onePokemonTeam");
        Type normalType = new Type("normal", 1,
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(Arrays.asList("ghost", "rock", "steel")));
        Type fightingType = new Type("fighting", 2,
                new HashSet<>(Arrays.asList("normal", "rock", "steel", "ice", "dark")),
                new HashSet<>(Arrays.asList("flying", "psychic", "fairy")),
                new HashSet<>(Arrays.asList("bug", "rock", "dark")));

        Pokemon pokemon = EmptyPokemonFactory.create();
        pokemon.setType1(normalType);
        pokemon.setType2(fightingType);
        pokemon.setStats(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));

        team.setPokemon(pokemon, 0);
        GradingStrategy strategy = new TeamGrader();
        GradeTeamInputData inputData = new GradeTeamInputData("onePokemonTeam", strategy);
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.saveTeam(team);

        GradeTeamOutputBoundary successPresenter = new GradeTeamOutputBoundary(){
            @Override
            public void prepareSuccessView(GradeTeamOutputData gradeTeamOutputData) {
                assertEquals(15.0, gradeTeamOutputData.getTeamScore());
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Use Case failure is unexpected.");
            }
        };

        GradeTeamInputBoundary interactor = new GradeTeamInteractor(dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
