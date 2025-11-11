package usecase.BuildPokemon;

import entity.Pokemon;
import usecase.BuildPokemonTeam.BuildPokemonTeamInputData;
import usecase.BuildPokemonTeam.BuildPokemonTeamInteractor;
import usecase.BuildPokemonTeam.BuildPokemonTeamOutputBoundary;
import usecase.BuildPokemonTeam.BuildPokemonTeamOutputData;

import java.util.ArrayList;

public class TestBuildPokemonTeam {
    public static void main(String[] args) {
        try {
            BuildPokemonTeamOutputBoundary presenter = new BuildPokemonTeamOutputBoundary() {
                @Override
                public void prepareSuccessView(BuildPokemonTeamOutputData data) {
                    System.out.println("✅ Success!");
                    System.out.println("Pokemon: " + data.getPokemon().getName());
                    System.out.println("Type1: " + data.getPokemon().getType1());
                    System.out.println("Type2: " + data.getPokemon().getType2());
                    System.out.println("Stats: " + data.getPokemon().getStats());
                    System.out.println("Abilities: " + data.getPokemon().getAbilities());
                    System.out.println("Hidden: " + data.getPokemon().getHidden());
                    System.out.println("Egggroup: " + data.getPokemon().getEgggroup());
                    System.out.println("Moves: " + data.getPokemon().getMoves());
                }

                @Override
                public void prepareFailView(String error) {
                    System.out.println("Blame Dani for the error lmao");
                }
            };

            String name = "pikachu" ;
            int type1 = 1;
            int type2 = 0;

            ArrayList<Integer> stats = new ArrayList<>();
            stats.add(1);
            stats.add(2);
            stats.add(3);
            stats.add(4);
            stats.add(5);
            stats.add(6);

            ArrayList<Integer> abilities = new ArrayList<>();
            abilities.add(1);
            abilities.add(2);

            int hidden = 0;

            ArrayList<Integer> moves = new ArrayList<>();
            moves.add(0);

            ArrayList<Integer> egggroup = new ArrayList<>();
            egggroup.add(1);

            ArrayList<Integer> pokedexes = new ArrayList<>();
            egggroup.add(1);

            
            Pokemon pikachu = new Pokemon(name, type1, type2, stats, abilities, hidden,  moves, egggroup, pokedexes);

            // Create the interactor
            BuildPokemonTeamInteractor interactor = new BuildPokemonTeamInteractor(presenter, pikachu);

            // Provide some test input data
            BuildPokemonTeamInputData input = new BuildPokemonTeamInputData("pikachu");

            // Run it
            interactor.execute(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
