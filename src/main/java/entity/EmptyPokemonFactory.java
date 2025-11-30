package entity;

import java.util.ArrayList;
import java.util.HashSet;

public class EmptyPokemonFactory {

    /**
     * Factory for Pokemon with 'empty' instance variables.
     * Note that type1 and type2 will not be null.
     * @return Pokemon object without its instance variables set to anything important
     */
    public static Pokemon create() {
        return new PokemonBuilder()
                .setName("")
                .setType1(new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""))
                .setType2(new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""))
                .setStats(new ArrayList<>())
                .setAbilities(new ArrayList<>())
                .setHidden(null)
                .setMoves(new ArrayList<>())
                .setEggGroups(new ArrayList<>())
                .setPokedexes(new ArrayList<>())
                .setSprite("")
                .build();
    }
}
