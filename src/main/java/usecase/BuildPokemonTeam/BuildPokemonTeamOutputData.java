package usecase.BuildPokemonTeam;

import entity.Pokemon;

public class BuildPokemonTeamOutputData {
    private final Pokemon Pokemon;

    public BuildPokemonTeamOutputData(Pokemon Pokemon) {
        this.Pokemon = Pokemon;
    }

    public Pokemon getPokemon() {
        return Pokemon;
    }

}