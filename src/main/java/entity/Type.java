package entity;

import java.util.HashSet;
import java.util.Set;

public class Type {
    private String name;
    private int id;
    private Set<String> strengths;
    private Set<String> weaknesses;
    private Set<String> resistances;
    private String sprite;

    public Type(String name, int id, HashSet<String> strengths, HashSet<String> weaknesses,
                HashSet<String> resistances, String sprite) {
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

    public int getId() {
        return id;
    }

    public Set<String> getStrengths() {
        return new HashSet<>(this.strengths);
    }

    public Set<String> getWeaknesses() {
        return new HashSet<>(this.weaknesses);
    }

    public Set<String> getResistances() {
        return new HashSet<>(this.resistances);
    }

    /**
     * Return the name with proper noun capitalization.
     *
     * @return String with the first letter capitalized
     */
    public String toProperName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return "Type{"
                + "name='" + name + '\''
                + "id=" + id
                + "strengths=" + strengths
                + "weaknesses=" + weaknesses
                + "resistances=" + resistances
                + '}';
    }
}
