package entity;

import java.util.HashSet;

public class Type {
    private String name;
    private int id;
    private HashSet<String> strengths;
    private HashSet<String> weaknesses;
    private HashSet<String> resistances;
    private String sprite;

    public Type(String name, int id, HashSet<String> strengths, HashSet<String> weaknesses, HashSet<String> resistances
    , String sprite) {
        this.name = name;
        this.id = id;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.resistances = resistances;
        this.sprite = sprite;
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

    public String getSprite ()
    {
        return sprite;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                "id=" + id +
                "strengths=" + strengths +
                "weaknesses=" + weaknesses +
                "resistances=" + resistances +
                '}';
    }
}
