package data_access;

import entity.Team;
import use_case.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;
import use_case.LoadTeam.LoadTeamDataAccessInterface;
import use_case.grade_team.GradeTeamUserDataAccessInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements GradeTeamUserDataAccessInterface, BuildPokemonTeamDataAccessInterface,
        LoadTeamDataAccessInterface {

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
    public Team loadTeam(String teamName) {
        return teams.get(teamName);
    }

    @Override
    public ArrayList<String> getAllTeamNames() {
        return new ArrayList<>(teams.keySet());
    }
}
