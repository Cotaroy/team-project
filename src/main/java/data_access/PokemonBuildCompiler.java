package data_access;

import entity.Ability;
import entity.Move;
import entity.Pokemon;
import entity.Type;
import usecase.lookup.PokemonLookupDataAccessInterface;
import usecase.lookup.PokemonLookupInputBoundary;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PokemonBuildCompiler {

    public static String getBuildCode(String name) throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        PokemonLookupDataAccessInterface pokemonLookupDataAccess = new PokemonLookupDataAccessObject();
        Pokemon pokemon = pokemonLookupDataAccess.getPokemon(name);

        Type type1 = pokemon.getType1();
        String type1String = new StringBuilder()
                .append("new Type(\"" + type1.getName() + "\", ")
                .append(type1.getId() + ",\n\t")
                .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type1.getStrengths()) + ")),\n\t\t")
                .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type1.getWeaknesses()) + ")),\n\t\t")
                .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type1.getResistances()) + ")),\n\t\t")
                .append('"' + type1.getSprite() + "\")")
                .toString();
        Type type2 = pokemon.getType2();
        String type2String = "null";
        if (type2 != null) {
            type2String = new StringBuilder()
                    .append("new Type(\"" + type2.getName() + "\", ")
                    .append(type2.getId() + ",\n\t")
                    .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type2.getStrengths()) + ")),\n\t\t")
                    .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type2.getWeaknesses()) + ")),\n\t\t")
                    .append(" new HashSet<>(Arrays.asList(" + setElementsToString(type2.getResistances()) + ")),\n\t\t")
                    .append('"' + type2.getSprite() + "\")")
                    .toString();
        }

        String hidden = "";
        if (pokemon.getHidden() != null) {
            hidden = ".setHidden(abilityMap.getAbility(" + pokemon.getHidden().getId() + "))\n\t";
        }

        String result = new StringBuilder()
                .append("final Pokemon " + name + " = new PokemonBuilder()\n\t")
                .append(".setName(\"" + pokemon.getName() + "\")\n\t")
                .append(".setType1(" + type1String + ")\n\t")
                .append(".setType2(" + type2String + ")\n\t")
                .append(".setStats(new ArrayList<>(Arrays.asList(" + listElementsToString(pokemon.getStats()) + ")))\n\t")
                .append(".setAbilities(new ArrayList<>(Arrays.asList("
                        + listElementsToStringAbility(pokemon.getAbilities()) + ")))\n\t")
                .append(hidden)
                .append(".setMoves(new ArrayList<>(Arrays.asList(" + listElementsToStringMove(pokemon.getMoves()) + ")))\n\t")
                .append(".setEggGroups(new ArrayList<>(Arrays.asList(" + listElementsToString(pokemon.getEgggroup()) + ")))\n\t")
                .append(".setPokedexes(new ArrayList<>(Arrays.asList(" + listElementsToString(pokemon.getPokedexes()) + ")))\n\t")
                .append(".setSprite(\"" + pokemon.getSprite() + "\")\n\t")
                .append(".build();")
                .toString();
        return result;
    }

    public static void main(String[] args) throws IOException, PokemonLookupInputBoundary.PokemonNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the pokemon you want to lookup");
        System.out.println(getBuildCode(scan.nextLine()));
    }

    private static String setElementsToString(Set<String> set) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (String element: set) {
            stringBuilder.append('"');
            stringBuilder.append(element);
            stringBuilder.append('"');
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static String listElementsToString(List<Integer> list) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int element: list) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static String listElementsToStringAbility(List<Ability> list) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Ability element: list) {
            stringBuilder.append("abilityMap.getAbility(");
            stringBuilder.append(element.getId());
            stringBuilder.append("), ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static String listElementsToStringMove(List<Move> list) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Move element: list) {
            if (element != null) {
                stringBuilder.append("moveMap.getMove(");
                stringBuilder.append(element.getId());
                stringBuilder.append("), ");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
