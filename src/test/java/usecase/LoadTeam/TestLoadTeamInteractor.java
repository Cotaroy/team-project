package usecase.LoadTeam;

import dataaccess.*;
import entity.*;
import org.junit.Test;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TestLoadTeamInteractor {

    @Test
    public void validTeamTest() throws BuildPokemonTeamDataAccessInterface.TeamExistsException, IOException {
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
        Team expectedTeam = new Team("goat");
        expectedTeam.setPokemon(magikarp, 0);

        LoadTeamDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.saveTeam(expectedTeam);

        LoadTeamInputData inputData = new LoadTeamInputData("goat");
        LoadTeamOutputBoundary presenter = new LoadTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadTeamOutputData outputData) {
                Team loadedTeam = outputData.getTeam();
                assertEquals("goat", loadedTeam.getTeamName());
                assertEquals(magikarp.toString(), loadedTeam.getPokemon(0).toString());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println("Failed to load team: " + errorMessage);
            }
        };
        LoadTeamInteractor interactor = new LoadTeamInteractor(dataAccess, presenter);
        interactor.execute(inputData);
    }

    @Test
    public void nonexistentTeamTest() throws BuildPokemonTeamDataAccessInterface.TeamExistsException, IOException {
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
        Team expectedTeam = new Team("goat");
        expectedTeam.setPokemon(magikarp, 0);

        LoadTeamDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.saveTeam(expectedTeam);

        LoadTeamInputData inputData = new LoadTeamInputData("nonexistent team");
        LoadTeamOutputBoundary presenter = new LoadTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadTeamOutputData outputData) {
                fail("Shouldn't be called");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertNotNull(errorMessage);
                assertFalse(errorMessage.isEmpty());
            }
        };
        LoadTeamInteractor interactor = new LoadTeamInteractor(dataAccess, presenter);
        interactor.execute(inputData);
    }

    @Test
    public void emptyNameTeamTest() throws BuildPokemonTeamDataAccessInterface.TeamExistsException, IOException {
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
        Team expectedTeam = new Team("goat");
        expectedTeam.setPokemon(magikarp, 0);

        LoadTeamDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.saveTeam(expectedTeam);

        LoadTeamInputData inputData = new LoadTeamInputData("");
        LoadTeamOutputBoundary presenter = new LoadTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadTeamOutputData outputData) {
                fail("Shouldn't be called");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("No Pokemon name provided.", errorMessage);
            }
        };
        LoadTeamInteractor interactor = new LoadTeamInteractor(dataAccess, presenter);
        interactor.execute(inputData);
    }

}