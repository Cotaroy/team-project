package data_access;
import java.io.*;
import java.util.ArrayList;

import entity.Pokemon;
import entity.Team;
import use_case.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

public class DataAccessObject implements BuildPokemonTeamDataAccessInterface {

    public void saveTeam(Team team) {
        String teamName = team.getTeamName();
        Pokemon p1 = team.getPokemon(0);
        Pokemon p2 = team.getPokemon(1);
        Pokemon p3 = team.getPokemon(2);
        Pokemon p4 = team.getPokemon(3);
        Pokemon p5 = team.getPokemon(4);
        Pokemon p6 = team.getPokemon(5);
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        pokemons.add(p1);
        pokemons.add(p2);
        pokemons.add(p3);
        pokemons.add(p4);
        pokemons.add(p5);
        pokemons.add(p6);

        try (FileReader x = new FileReader("teamStorage/teams.csv")) {
            System.out.println("SUCCESS");
            System.out.println(teamName);
            System.out.println(pokemons + "\n");


            FileWriter writer = new FileWriter("teamStorage/teams.csv", true);

            writer.write(team.getTeamName() + "," + " ");

            for (int i = 0; i < pokemons.size(); i++) {
                if (i > 0) writer.write("," + " "); {
                    writer.write(team.getPokemon(i).getName());
                }
            }


            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Team loadTeam(String name) {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("teamStorage/teams.csv"));) {
            //parse lines until reaching the team
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("teamName")) {
                    break;
                }
            }
            String[] parts = line.split(",");
            int partsLength = parts.length - 1;
            Team team = new Team(name);

            //Add each pokemon from the line to a new team
            for(int i = 1; i < partsLength; i++){
                team.setTeamName(parts[i]);     //set pokemon
            }
            return team;
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load team", e);
        }
    }

        public boolean exists (String name){
            boolean x = true;
            return x;
        }


    }