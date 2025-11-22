package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pokemon {

    static final int HP_INDEX_NUMBER = 0;
    static final int ATTACK_INDEX_NUMBER = 1;
    static final int DEFENSE_INDEX_NUMBER = 2;
    static final int SPECIAL_ATTACK_INDEX_NUMBER = 3;
    static final int SPECIAL_DEFENSE_INDEX_NUMBER = 4;
    static final int SPEED_INDEX_NUMBER = 5;

    // Private fields
    private String name;
    private Type type1;
    private Type type2;
    private ArrayList<Integer> stats;
    // 0: hp, 1: attack, 2: defense, 3: special-attack, 4: special-defense, 5: speed
    private ArrayList<Ability> abilities;
    private Ability hidden;
    private ArrayList<Move> moves;
    private ArrayList<Integer> egggroup;
    private ArrayList<Integer> pokedexes;
    private String sprite;

    // Constructor
    public Pokemon(String name, Type type1, Type type2, ArrayList<Integer> stats,
                   ArrayList<Ability> abilities, Ability hidden, ArrayList<Move> moves,
                   ArrayList<Integer> egggroup, ArrayList<Integer> pokedexes, String sprite) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.stats = stats;
        this.abilities = abilities;
        this.hidden = hidden;
        this.moves = moves;
        this.egggroup = egggroup;
        this.pokedexes = pokedexes;
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return "Pokemon{"
                + "name='" + name + '\''
                + ", type1=" + type1
                + ", type2=" + type2
                + ", stats=" + stats
                + ", abilities=" + abilities
                + ", hidden=" + hidden
                + ", moves=" + moves
                + ", egggroups=" + egggroup
                + ", pokedexes=" + pokedexes
                + '}';
    }

    public Pokemon getCopy() {
        return new Pokemon(
                name,
                type1,
                type2,
                stats,
                abilities,
                hidden,
                moves,
                egggroup,
                pokedexes,
                sprite
        );
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    // returns the Pokemon's name as a proper, formatted noun as opposed to its internal name

    /**
     * Get the name with proper noun capitalization.
     *
     * @return the name instance variable but capitalized after spaces or dashes
     */
    public String getProperName() {

        // Exact-format exceptions (NOT the mo-o line)
        final Map<String, String> fixedExceptions = new HashMap<>();
        fixedExceptions.put("ho-oh", "Ho-Oh");
        fixedExceptions.put("porygon-z", "Porygon-Z");
        fixedExceptions.put("ting-lu", "Ting-Lu");
        fixedExceptions.put("chi-yu", "Chi-Yu");
        fixedExceptions.put("wo-chien", "Wo-Chien");
        fixedExceptions.put("chien-pao", "Chien-Pao");
        fixedExceptions.put("jangmo-o", "Jangmo-o");
        fixedExceptions.put("hakamo-o", "Hakamo-o");
        fixedExceptions.put("kommo-o", "Kommo-o");

        final String splitBy = "-";

        String result = "";

        if (name == null || name.isEmpty()) {
            result = name;
        }

        // If the name is in fixed exceptions, return exact formatted version
        else if (fixedExceptions.containsKey(name.toLowerCase())) {
            result = fixedExceptions.get(name.toLowerCase());
        }

        // If there is no hyphen, format as a normal name
        else if (!name.contains(splitBy)) {
            result = capitalize(name);
        }

        // rule: name-mega → Mega Name
        else if ("mega".equalsIgnoreCase(name.split(splitBy, 2)[1])) {
            final String[] parts = name.split(splitBy, 2);
            final String first = parts[0];
            final String second = parts[1];
            result = capitalize(second) + " " + capitalize(first);
        }

        // rule: name-anythingElse → Name anythingElse Form
        else {
            final String[] parts = name.split(splitBy, 2);
            final String first = parts[0];
            final String second = parts[1];
            result = capitalize(first) + " " + capitalize(second) + " Form";
        }
        return result;
    }

    // Capitalize first letter
    private static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    // Capitalizes both sides of a hyphen normally (for jangmo-o, etc.)
    private static String capitalizeHyphenated(String string) {
        final String splitBy = "-";
        final String[] parts = string.split(splitBy);
        return capitalize(parts[0]) + splitBy + capitalize(parts[1]);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public ArrayList<Integer> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Integer> stats) {
        this.stats = stats;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public Ability getHidden() {
        return hidden;
    }

    public void setHidden(Ability hidden) {
        this.hidden = hidden;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public ArrayList<Integer> getEgggroup() {
        return egggroup;
    }

    public void setEgggroup(ArrayList<Integer> egggroup) {
        this.egggroup = egggroup;
    }

    public void setPokedexes(ArrayList<Integer> pokedexes) {
        this.pokedexes = pokedexes;
    }

    public List<Integer> getPokedexes() {
        return pokedexes;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }

    public int getHp() {
        return stats.get(HP_INDEX_NUMBER);
    }

    public int getAttack() {
        return stats.get(ATTACK_INDEX_NUMBER);
    }

    public int getDefense() {
        return stats.get(DEFENSE_INDEX_NUMBER);
    }

    public int getSpecialAttack() {
        return stats.get(SPECIAL_ATTACK_INDEX_NUMBER);
    }

    public int getSpecialDefense() {
        return stats.get(SPECIAL_DEFENSE_INDEX_NUMBER);
    }

    public int getSpeed() {
        return stats.get(SPEED_INDEX_NUMBER);
    }

    /**
     * Get weaknesses of the Pokemon.
     * @return Set of strings that the Pokemon is weak to
     */
    public Set<String> getWeaknesses() {
        final Set<String> weaknesses1 = new HashSet<>(type1.getWeaknesses());
        final Set<String> weaknesses2 = new HashSet<>(type2.getWeaknesses());
        final Set<String> resistances1 = new HashSet<>(type1.getResistances());
        final Set<String> resistances2 = new HashSet<>(type2.getResistances());
        final Set<String> compare12 = new HashSet<>(weaknesses1);
        compare12.retainAll(resistances2);
        final Set<String> compare21 = new HashSet<>(weaknesses2);
        compare21.retainAll(resistances1);
        final Set<String> weaknessesf1 = new HashSet<>(weaknesses1);
        weaknessesf1.removeAll(compare12);
        final Set<String> weaknessesf2 = new HashSet<>(weaknesses2);
        weaknessesf2.removeAll(compare21);
        final Set<String> weaknesses = new HashSet<>(weaknessesf1);
        weaknesses.addAll(weaknessesf2);
        return weaknesses;
    }

    /**
     * Get strengths of the Pokemon.
     * @return Set of strings that the Pokemon is strong against
     */
    public Set<String> getStrengths() {
        final Set<String> strengths = new HashSet<>(type1.getStrengths());
        strengths.addAll(type2.getStrengths());
        return strengths;
    }

    /**
     * Get weaknesses of the Pokemon.
     * @return Set of string that the Pokemon resists
     */
    public Set<String> getResistances() {
        final Set<String> weaknesses1 = new HashSet<>(type1.getWeaknesses());
        final Set<String> weaknesses2 = new HashSet<>(type2.getWeaknesses());
        final Set<String> resistances1 = new HashSet<>(type1.getResistances());
        final Set<String> resistances2 = new HashSet<>(type2.getResistances());

        // Compare overlaps between resistances and the other type’s weaknesses
        final Set<String> compare12 = new HashSet<>(resistances1);
        compare12.retainAll(weaknesses2);

        final Set<String> compare21 = new HashSet<>(resistances2);
        compare21.retainAll(weaknesses1);

        // Remove cancelled resistances
        final Set<String> resistancesf1 = new HashSet<>(resistances1);
        resistancesf1.removeAll(compare12);

        final Set<String> resistancesf2 = new HashSet<>(resistances2);
        resistancesf2.removeAll(compare21);

        // Combine remaining resistances
        final Set<String> resistances = new HashSet<>(resistancesf1);
        resistances.addAll(resistancesf2);

        return resistances;
    }
}

