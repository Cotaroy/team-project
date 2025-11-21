package data_access;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Pokemon;
import entity.Team;
import use_case.BuildPokemonTeam.BuildPokemonTeamDataAccessInterface;

public class BuildPokemonTeamDataAccessObject implements BuildPokemonTeamDataAccessInterface {

    @Override
    public void saveTeam(Team team) {
        String teamName = team.getTeamName();
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        for (int i = 0; i < 6; i++) {
            Pokemon x = team.getPokemon(i);
            pokemons.add(x);
        }

        try (FileReader x = new FileReader("teamStorage/teams.csv")) {
            if (teamExists(team)) {
                System.out.println("Please change the team name.");
                return;
            }

            System.out.println("SUCCESS");
            System.out.println(teamName);
            System.out.println(pokemons + "\n");

            FileWriter writer = new FileWriter("teamStorage/teams.csv", true);



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
    public boolean teamExists(Team team) throws FileNotFoundException {
        FileReader x = new FileReader("teamStorage/teams.csv");
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

}

