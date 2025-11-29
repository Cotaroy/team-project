package data_access;

import entity.Pokemon;
import entity.Team;
import entity.Type;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;
import usecase.PokemonLookup.PokemonLookupDataAccessInterface;
import usecase.grade_team.GradeTeamUserDataAccessInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements GradeTeamUserDataAccessInterface, BuildPokemonTeamDataAccessInterface, PokemonLookupDataAccessInterface {
    private MoveMap movemap = new MoveMap();
    private AbilityMap abilitymap = new AbilityMap();
    private PokemonMap pokemap = new PokemonMap(abilitymap, movemap);
    private final Map<String, Team> teams = new HashMap<>();

    @Override
    public void saveTeam(Team team) {
        teams.put(team.getTeamName(), team);
    }

    @Override
    public boolean teamExists(Team name) throws FileNotFoundException {
        if (teams.containsKey(name.getTeamName())){
            return true;
        }
        return false;
    }

    @Override
    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    @Override
    public Pokemon getPokemon(String name) throws IOException {
        return pokemap.getPokemon(name);
    }
}
