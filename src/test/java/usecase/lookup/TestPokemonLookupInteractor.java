package usecase.lookup;

import data_access.AbilityMap;
import data_access.MoveMap;
import data_access.PokemonLookupDataAccessObject;
import entity.Pokemon;
import entity.EmptyPokemonFactory;

import java.io.IOException;
import java.util.*;
import java.util.HashSet;

import entity.Type;
import entity.Move;
import entity.Ability;
import org.junit.jupiter.api.Test;
import usecase.PokemonLookup.*;

import static org.junit.jupiter.api.Assertions.*;

class TestPokemonLookupInteractor {


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
        Pokemon magikarp = new Pokemon("magikarp", type1, null, statsikarp, abilitykarp, abilitymap.getAbility(155), moveskarp, eggkarp, pokedexkarp, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/129.png");
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
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
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


    // dual type return the right weakness test
    @Test
    void LudicoloTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
//        HashSet<String> waterstrength = new HashSet<>(Arrays.asList("fire", "rock", "ground"));
//        HashSet<String> waterweak = new HashSet<>(Arrays.asList("grass", "electric"));
//        HashSet<String> wateres = new HashSet<>(Arrays.asList("fire", "rock", "ground"));
//
//        HashSet<String> grassstrength = new HashSet<>(Arrays.asList("ground", "rock", "water"));
//        HashSet<String> grassweak = new HashSet<>(Arrays.asList("flying", "fire", "bug", "ice", "poison"));
//        HashSet<String> grassres = new HashSet<>(Arrays.asList("ground", "grass", "water", "electric"));
//        Type type1 = new Type("water", 11,
//                waterstrength, waterweak, wateres);
//        Type type2 = new Type("grass", 12,
//                grassstrength, grassweak, grassres);
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
//        emptymon.setType1(type1);
//        emptymon.setType2(type2);
        HashSet<String> ludicoloweakness = new HashSet<>(Arrays.asList("poison", "flying", "bug"));
        PokemonLookupInputData inputData = new PokemonLookupInputData("ludicolo");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(ludicoloweakness, outputData.getPokemon().getWeaknesses());
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

    // dual type return the right strengths test (no duplicates)
    @Test
    void AurorusTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
//        HashSet<String> icestrength = new HashSet<>(Arrays.asList("flying", "ground", "grass", "dragon"));
//        HashSet<String> iceweak = new HashSet<>(Arrays.asList("fighting", "fire", "rock", "steel"));
//        HashSet<String> iceres = new HashSet<>(Arrays.asList("ice"));
//
//        HashSet<String> rockstrength = new HashSet<>(Arrays.asList("flying", "bug", "fire", "ice"));
//        HashSet<String> rockweak = new HashSet<>(Arrays.asList("water", "grass", "ground", "steel", "fighting"));
//        HashSet<String> rockres = new HashSet<>(Arrays.asList("ground", "grass", "water", "electric"));
//        Type type2 = new Type("ice", 15,
//                icestrength, iceweak, iceres);
//        Type type1 = new Type("rock", 6,
//                rockstrength, rockweak, rockres);
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
        HashSet<String> aurorusstrength = new HashSet<>(Arrays.asList("flying", "ground", "grass", "dragon", "bug", "fire", "ice"));
//        emptymon.setType1(type1);
//        emptymon.setType2(type2);
        PokemonLookupInputData inputData = new PokemonLookupInputData("aurorus");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(aurorusstrength, outputData.getPokemon().getStrengths());
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

    // dual types return the right resistances

    @Test
    void DurantTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
//        HashSet<String> bugstrength = new HashSet<>(Arrays.asList("grass", "dark", "psychic"));
//        HashSet<String> bugweak = new HashSet<>(Arrays.asList("fire", "rock", "flying"));
//        HashSet<String> bugres = new HashSet<>(Arrays.asList("ground", "grass", "fighting"));
//
//        HashSet<String> steelstrength = new HashSet<>(Arrays.asList("rock", "ice", "fairy"));
//        HashSet<String> steelweak = new HashSet<>(Arrays.asList("fire", "fighting", "ground"));
//        HashSet<String> steelres = new HashSet<>(Arrays.asList("normal", "flying", "rock", "bug", "steel", "grass", "psychic", "ice", "dragon", "fairy"));
//        Type type1 = new Type("bug", 7,
//                bugstrength, bugweak, bugres);
//        Type type2 = new Type("steel", 9,
//                steelstrength, steelweak, steelres);
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
//        emptymon.setType1(type1);
//        emptymon.setType2(type2);
        HashSet<String> durantres = new HashSet<>(Arrays.asList("normal", "bug", "poison", "steel", "grass", "psychic", "ice", "dragon", "fairy"));
        PokemonLookupInputData inputData = new PokemonLookupInputData("durant");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(durantres, outputData.getPokemon().getResistances());
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

    // pokemon only has one ability
    @Test
    void ShedinjaTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        AbilityMap abilitymap = new AbilityMap();
        ArrayList<Ability> abilitykarp = new ArrayList<>(Arrays.asList(abilitymap.getAbility(25)));
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
        emptymon.setAbilities(abilitykarp);
        PokemonLookupInputData inputData = new PokemonLookupInputData("shedinja");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(emptymon.getAbilities().get(0).toString(), outputData.getPokemon().getAbilities().get(0).toString());
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

    // pokemon only has one ability and one hidden ability
    @Test
    void GarchompTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        AbilityMap abilitymap = new AbilityMap();
        ArrayList<Ability> ability = new ArrayList<>(Arrays.asList(abilitymap.getAbility(8)));
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
        emptymon.setAbilities(ability);
        emptymon.setHidden(abilitymap.getAbility(24));
        PokemonLookupInputData inputData = new PokemonLookupInputData("garchomp");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(emptymon.getAbilities().get(0).toString(), outputData.getPokemon().getAbilities().get(0).toString());
                assertEquals(abilitymap.getAbility(24).toString(), outputData.getPokemon().getHidden().toString());
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

    // pokemon only has all three abilities
    @Test
    void ScolipedeTest() throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        AbilityMap abilitymap = new AbilityMap();
        ArrayList<Ability> ability = new ArrayList<>(Arrays.asList(abilitymap.getAbility(38),
                abilitymap.getAbility(68)));
        EmptyPokemonFactory factoree = new EmptyPokemonFactory();
        Pokemon emptymon = factoree.create();
        emptymon.setAbilities(ability);
        emptymon.setHidden(abilitymap.getAbility(3));
        PokemonLookupInputData inputData = new PokemonLookupInputData("scolipede");
        PokemonLookupDataAccessInterface dataAccess = new PokemonLookupDataAccessObject();
        PokemonLookupOutputBoundary successPresenter = new PokemonLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(PokemonLookupOutputData outputData) {
                assertEquals(emptymon.getAbilities().get(0).toString(), outputData.getPokemon().getAbilities().get(0).toString());
                assertEquals(emptymon.getAbilities().get(1).toString(), outputData.getPokemon().getAbilities().get(1).toString());
                assertEquals(abilitymap.getAbility(3).toString(), outputData.getPokemon().getHidden().toString());
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



