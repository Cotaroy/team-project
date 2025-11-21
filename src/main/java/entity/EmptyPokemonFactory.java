package entity;

import java.util.ArrayList;
import java.util.HashSet;

public class EmptyPokemonFactory {
    public static Pokemon create(){
        return new Pokemon("", new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""),
                new Type("", 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ""),
                new ArrayList<Integer>(), new ArrayList<Ability>(),
                null, new ArrayList<Move>(), new ArrayList<Integer>(),
                new ArrayList<Integer>(), "");
    }
}
