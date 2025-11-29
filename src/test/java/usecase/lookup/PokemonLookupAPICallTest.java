package usecase.lookup;

import data_access.AbilityMap;
import data_access.MoveMap;
import data_access.PokemonLookupDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PokemonLookupAPICallTest {
    // a basic pokemon test
    @Test
    void MagikarpTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        MoveMap movemap = new MoveMap();
        AbilityMap abilitymap = new AbilityMap();
        ArrayList<Integer> statsikarp = new ArrayList<>(Arrays.asList(20, 10, 55, 15, 20, 80));
        ArrayList<Ability> abilitykarp = new ArrayList<>(Arrays.asList(abilitymap.getAbility(33)));
        ArrayList<Move> moveskarp = new ArrayList<>(Arrays.asList(movemap.getMove(33), movemap.getMove(56), movemap.getMove(150), movemap.getMove(175), movemap.getMove(340)));
        ArrayList<Integer> eggkarp = new ArrayList<>(Arrays.asList(12, 14));
        ArrayList<Integer> pokedexkarp = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
                29, 30, 31, 32, 34));
        HashSet<String> waterstrength = new HashSet<>(Arrays.asList("fire", "rock", "ground"));
        HashSet<String> waterweak = new HashSet<>(Arrays.asList("grass", "electric"));
        HashSet<String> wateres = new HashSet<>(Arrays.asList("steel", "fire", "ice", "water"));

        Type type1 = new Type("water", 11,
                waterstrength, waterweak, wateres, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/legends-arceus/11.png");
        Pokemon magikarp = new PokemonBuilder()
                .setName("magikarp")
                .setType1(type1)
                .setType2(null)
                .setStats(statsikarp)
                .setAbilities(abilitykarp)
                .setHidden(abilitymap.getAbility(155))
                .setMoves(moveskarp)
                .setEggGroups(eggkarp)
                .setPokedexes(pokedexkarp)
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/129.png")
                .build();
        PokemonLookupInputData inputData = new PokemonLookupInputData("magikarp");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary()
        {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(magikarp.toString(), outputData.getPokemon().toString());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToTeamBuilderView(int index, Pokemon pokemon) {

            }
        };
        PokemonLookupInputBoundary interactor = new PokemonLookupInteractor(successPresenter, magikarp, dataAccess);
        interactor.execute(inputData);
    }

    // returns the specific form data
    @Test
    void AegislashTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(60, 50, 140, 50, 140, 60));
        Pokemon emptymon = EmptyPokemonFactory.create();
        PokemonLookupInputData inputData = new PokemonLookupInputData("aegislash-shield");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals("aegislash-shield", outputData.getPokemon().getName());
                assertEquals(stats, outputData.getPokemon().getStats());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToTeamBuilderView(int index, Pokemon pokemon) {

            }
        };
        PokemonLookupInputBoundary interactor = new PokemonLookupInteractor(successPresenter, emptymon, dataAccess);
        interactor.execute(inputData);

    }

    // returns a formless pokemon that still has a hyphen
    @Test
    void TingLuTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
        PokemonLookupInputData inputData = new PokemonLookupInputData("ting-lu");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals("ting-lu", outputData.getPokemon().getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToTeamBuilderView(int index, Pokemon pokemon) {

            }
        };
        PokemonLookupInputBoundary interactor = new PokemonLookupInteractor(successPresenter, emptymon, dataAccess);
        interactor.execute(inputData);

    }
}
