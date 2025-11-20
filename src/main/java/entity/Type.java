package entity;

import java.util.HashSet;

public class Type {
    private String name;
    private int id;
    private HashSet<String> strengths;
    private HashSet<String> weaknesses;
    private HashSet<String> resistances;

    public Type(String name, int id, HashSet<String> strengths, HashSet<String> weaknesses, HashSet<String> resistances) {
        this.name = name;
        this.id = id;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.resistances = resistances;
    }

    public String getName() {
        return name;
    }

    public int  getId() {
        return id;
    }

    public HashSet<String> getStrengths() {
        return strengths;
    }

    public HashSet<String> getWeaknesses() {
        return weaknesses;
    }

    public HashSet<String> getResistances() {
        return resistances;
    }

    public String toProperName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
