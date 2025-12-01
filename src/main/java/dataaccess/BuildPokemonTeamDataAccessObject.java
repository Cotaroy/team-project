package dataaccess;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.Pokemon;
import entity.Team;
import org.jetbrains.annotations.NotNull;
import usecase.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;
import usecase.LoadTeam.LoadTeamDataAccessInterface;
import usecase.lookup.PokemonLookupDataAccessInterface;

public class BuildPokemonTeamDataAccessObject implements BuildPokemonTeamDataAccessInterface,
        LoadTeamDataAccessInterface {

    private final String filePath;

    public BuildPokemonTeamDataAccessObject() {
        this("teamStorage/teams.csv");
    }

    public BuildPokemonTeamDataAccessObject(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void saveTeam(Team team) throws TeamExistsException {
        String teamName = team.getTeamName();
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        for (int i = 0; i < 6; i++) {
            Pokemon x = team.getPokemon(i);
            pokemons.add(x);
        }

        try (FileReader x = new FileReader(filePath)) {
            if (teamExists(team)) {
                System.out.println(team.getTeamName() + " already exists");
                throw new TeamExistsException(team.getTeamName());
            }

            System.out.println("SUCCESS");
            System.out.println(teamName);
            System.out.println(pokemons + "\n");

            FileWriter writer = new FileWriter(filePath, true);

            writer.write(team.getTeamName() + "," + " ");

            for (int i = 0; i < pokemons.size(); i++) {
                if (i > 0) writer.write("," + " "); {
                    if (team.getPokemon(i) == null){
                        writer.write("Null");
                    }
                    else {
                        writer.write(team.getPokemon(i).getName());
                    }
                }
            }


            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Team loadTeam(String teamName) {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(teamName + ",")) {
                    break;
                }
            }
            if (line == null) {
                return null;
            }
            else {
                return getTeam(teamName, line);
            }
        }
        catch (IOException | PokemonLookupDataAccessInterface.PokemonNotFoundException e) {
            throw new RuntimeException("Failed to load team", e);
        }
    }

    @NotNull
    private static Team getTeam(String teamName, String line) throws IOException, PokemonLookupDataAccessInterface.PokemonNotFoundException {
        List<String> names = new ArrayList<>(Arrays.asList(line.split(",")));
        names.remove(0);  // Remove team name
        names.replaceAll(String::trim);

        Team result = new Team(teamName);

        int i = 0;
        PokemonLookupDataAccessObject lookup = new PokemonLookupDataAccessObject();
        for(String name : names){
            if (!name.equals("Null")) {
                Pokemon pokemon = lookup.getPokemon(name);
                result.setPokemon(pokemon, i);
                i += 1;
            }
        }
        return result;
    }

    @Override
    public void overwriteTeam(Team team) {
        String teamName = team.getTeamName();
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        for (int i = 0; i < 6; i++) {
            Pokemon x = team.getPokemon(i);
            pokemons.add(x);
        }

        try {
            FileReader file = new FileReader("teamStorage/teams.csv");
            Scanner sc = new Scanner(file);


            ArrayList<String> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(teamName)) {
                    StringBuilder overwriteTeam = new StringBuilder();
                    overwriteTeam.append(team.getTeamName()).append(",").append(" ");
                    for (int i = 0; i < pokemons.size(); i++) {
                        if (i > 0) overwriteTeam.append("," + " "); {
                            if (team.getPokemon(i) == null){
                                overwriteTeam.append("Null");
                            }
                            else {
                                overwriteTeam.append(pokemons.get(i).getName());
                            }
                        }
                    }
                    lines.add(overwriteTeam.toString());
                }
                else {
                    lines.add(line);
                }
            }

            FileWriter writer = new FileWriter("teamStorage/teams.csv");

            for (String line : lines) {
                writer.write(line + "\n");
            }

            writer.close();
            System.out.println("SUCCESS");
            System.out.println(teamName);
            System.out.println(pokemons + "\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean teamExists(Team team) throws FileNotFoundException {
        FileReader x = new FileReader(filePath);
        Scanner sc = new Scanner(x);
        String teamName = team.getTeamName();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] data = line.split(",");
            boolean equals = data[0].equals(teamName);

            if (equals) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getAllTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String teamName = line.split(",")[0].trim();
                    teamNames.add(teamName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read team names", e);
        }
        return teamNames;
    }
}

