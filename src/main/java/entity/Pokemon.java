package entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Pokemon {
        // Private fields
        private String name;
        private Type type1;
        private Type type2;
        private ArrayList<Integer> stats;
        // 0: hp, 1: attack, 2: defense, 3: special-attack, 4: special-defense, 5: speed
        private ArrayList<Integer> abilities;
        private int hidden;
        private ArrayList<Integer> moves;
        private ArrayList<Integer> egggroup;
        private ArrayList<Integer> pokedexes;
        private String sprite;
        // Constructor
        public Pokemon(String name, Type type1, Type type2, ArrayList<Integer> stats,
                       ArrayList<Integer> abilities, int hidden, ArrayList<Integer> moves, ArrayList<Integer> egggroup, ArrayList<Integer> pokedexes, String sprite) {
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
            return "Pokemon{" +
                    "name='" + name + '\'' +
                    ", type1=" + type1 +
                    ", type2=" + type2 +
                    ", stats=" + stats +
                    ", abilities=" + abilities +
                    ", hidden=" + hidden +
                    ", moves=" + moves +
                    ", egggroups=" + egggroup +
                    ", pokedexes=" + pokedexes +
                    '}';
        }

        // Getters and Setters
        public String getName() {
            return name;
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

        public ArrayList<Integer> getAbilities() {
            return abilities;
        }

        public void setAbilities(ArrayList<Integer> abilities) {
            this.abilities = abilities;
        }

        public int getHidden() {
            return hidden;
        }

        public void setHidden(int hidden) {
            this.hidden = hidden;
        }

        public List<Integer> getMoves() {
            return moves;
        }

        public void setMoves(ArrayList<Integer> moves) {
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

        public int getHP() { return stats.get(0);}
        public int getAttack() { return stats.get(1);}
        public int getDefense() { return stats.get(2);}
        public int getSpecialAttack() { return stats.get(3);}
        public int getSpecialDefense() { return stats.get(4);}
        public int getSpeed() { return stats.get(5);}

    public HashSet<String> getWeaknesses() {
        HashSet<String> weaknesses1 = new HashSet<>(type1.getWeaknesses());
        HashSet<String> weaknesses2 = new HashSet<>(type2.getWeaknesses());
        HashSet<String> resistances1 = new HashSet<>(type1.getResistances());
        HashSet<String> resistances2 = new HashSet<>(type2.getResistances());
        HashSet<String> compare12 = new HashSet<>(weaknesses1);
        compare12.retainAll(resistances2);
        HashSet<String> compare21 = new HashSet<>(weaknesses2);
        compare21.retainAll(resistances1);
        HashSet<String> weaknessesf1 = new HashSet<>(weaknesses1);
        weaknessesf1.removeAll(compare12);
        HashSet<String> weaknessesf2 = new HashSet<>(weaknesses2);
        weaknessesf2.removeAll(compare21);
        HashSet<String> weaknesses = new HashSet<>(weaknessesf1);
        weaknesses.addAll(weaknessesf2);
        return weaknesses;
    }

        public HashSet<String> getStrengths() {
        HashSet<String> strengths = new HashSet<>(type1.getStrengths());
        strengths.addAll(type2.getStrengths());
        return strengths;
        }

        public HashSet<String> getResistances() {
            HashSet<String> weaknesses1 = new HashSet<>(type1.getWeaknesses());
            HashSet<String> weaknesses2 = new HashSet<>(type2.getWeaknesses());
            HashSet<String> resistances1 = new HashSet<>(type1.getResistances());
            HashSet<String> resistances2 = new HashSet<>(type2.getResistances());

// Compare overlaps between resistances and the other type’s weaknesses
            HashSet<String> compare12 = new HashSet<>(resistances1);
            compare12.retainAll(weaknesses2);

            HashSet<String> compare21 = new HashSet<>(resistances2);
            compare21.retainAll(weaknesses1);

// Remove cancelled resistances
            HashSet<String> resistancesf1 = new HashSet<>(resistances1);
            resistancesf1.removeAll(compare12);

            HashSet<String> resistancesf2 = new HashSet<>(resistances2);
            resistancesf2.removeAll(compare21);

// Combine remaining resistances
            HashSet<String> resistances = new HashSet<>(resistancesf1);
            resistances.addAll(resistancesf2);

            return resistances;
        }

    // Helper methods to add single elements
        public void addStat(int stat) {
            this.stats.add(stat);
        }

        public void addAbility(int ability) {
            this.abilities.add(ability);
        }

        public void addMove(int move) {
            this.moves.add(move);
        }
    }

