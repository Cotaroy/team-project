package dataaccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import entity.Pokemon;
import entity.Team;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;
import usecase.LoadTeam.LoadTeamDataAccessInterface;
import usecase.lookup.PokemonLookupDataAccessInterface;
import usecase.grade_team.GradeTeamUserDataAccessInterface;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements GradeTeamUserDataAccessInterface,
        BuildPokemonTeamDataAccessInterface, PokemonLookupDataAccessInterface {
    private PokemonMap pokemap = new PokemonMap();

    private final Map<String, Team> teams = new HashMap<>();

    @Override
    public void saveTeam(Team team) {
        teams.put(team.getTeamName(), team);
    }

    @Override
    public boolean teamExists(Team name) {
        return teams.containsKey(name.getTeamName());
    }

    @Override
    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    @Override
    public Pokemon getPokemon(String name) throws IOException {
        return pokemap.getPokemon(name);
    }

    @Override
    public Team loadTeam(String teamName) {
        return teams.get(teamName);
    }

    @Override
    public ArrayList<String> getAllTeamNames() {
        return new ArrayList<>(teams.keySet());
    }
}
