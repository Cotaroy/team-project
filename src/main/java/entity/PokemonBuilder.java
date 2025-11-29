package entity;

import java.util.ArrayList;

public class PokemonBuilder {

    private Pokemon pokemon = new Pokemon();

    /**
     * Set the name of the Pokemon.
     * @param name String that is the name
     * @return PokemonBuilder with pokemon having name
     */
    public PokemonBuilder setName(String name) {
        pokemon.setName(name);
        return this;
    }

    /**
     * Set the first type of the Pokemon.
     * @param type Type object that we are setting
     * @return PokemonBuilder with pokemon having type as its type1
     */
    public PokemonBuilder setType1(Type type) {
        pokemon.setType1(type);
        return this;
    }

    /**
     * Set the first type of the Pokemon.
     * @param type Type object that we are setting
     * @return PokemonBuilder with pokemon having type as its type2
     */
    public PokemonBuilder setType2(Type type) {
        pokemon.setType2(type);
        return this;
    }

    /**
     * Set the stats of the Pokemon.
     * @param stats List of integers that represent the stats of the Pokemon
     * @return PokemonBuilder with pokemon having stats
     */
    public PokemonBuilder setStats(ArrayList<Integer> stats) {
        pokemon.setStats(stats);
        return this;
    }

    /**
     * Set the abilities of the Pokemon.
     * @param abilities ArrayList of Ability objects to be set
     * @return PokemonBuilder with abilities set to the corresponding instance variable
     */
    public PokemonBuilder setAbilities(ArrayList<Ability> abilities) {
        pokemon.setAbilities(abilities);
        return this;
    }

    /**
     * Set the hidden attribute of the Pokemon.
     * @param hidden Ability object to be set
     * @return PokemonBuilder with hidden set to the corresponding instance variable
     */
    public PokemonBuilder setHidden(Ability hidden) {
        pokemon.setHidden(hidden);
        return this;
    }

    /**
     * Set the moves of the Pokemon.
     * @param moves ArrayList of Move objects to be set
     * @return PokemonBuilder with moves set to the corresponding instance variable
     */
    public PokemonBuilder setMoves(ArrayList<Move> moves) {
        pokemon.setMoves(moves);
        return this;
    }

    /**
     * Set the eggGroups of the Pokemon.
     * @param eggGroups ArrayList of Integers to be set
     * @return PokemonBuilder with eggGroups set to the corresponding instance variable
     */
    public PokemonBuilder setEggGroups(ArrayList<Integer> eggGroups) {
        pokemon.setEgggroup(eggGroups);
        return this;
    }

    /**
     * Set the pokedexes of the Pokemon.
     * @param pokedexes ArrayList of Integers to be set
     * @return PokemonBuilder with pokedexes set to the corresponding instance variable
     */
    public PokemonBuilder setPokedexes(ArrayList<Integer> pokedexes) {
        pokemon.setPokedexes(pokedexes);
        return this;
    }

    /**
     * Set the sprite of the Pokemon.
     * @param sprite url of the sprite
     * @return PokemonBuilder with sprite set to the corresponding instance variable
     */
    public PokemonBuilder setSprite(String sprite) {
        pokemon.setSprite(sprite);
        return this;
    }

    /**
     * Build the Pokemon Object.
     * @return Pokemon Object that has been built
     */
    public Pokemon build() {
        return pokemon;
    }
}
