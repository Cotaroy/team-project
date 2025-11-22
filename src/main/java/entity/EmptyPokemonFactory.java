package entity;

import java.util.ArrayList;
import java.util.HashSet;

public class EmptyPokemonFactory {
    /**
     * Factory for Pokemon with 'empty' instance variables.
     * @return Pokemon object without its instance variables set to anything important
     */
    public static Pokemon create() {
        return new Pokemon("", new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""),
                new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""),
                new ArrayList<Integer>(), new ArrayList<Ability>(),
                null, new ArrayList<Move>(), new ArrayList<Integer>(),
                new ArrayList<Integer>(), "");
    }
}
