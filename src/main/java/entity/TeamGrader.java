package entity;

import java.util.ArrayList;
import java.util.HashSet;


public class TeamGrader implements GradingStrategy{
    @Override
    public float execute(Team team) {
        float finalScore = 0;

        Pokemon[] pokemon = team.getPokemon();

        finalScore += getCoverageScore(pokemon);
        finalScore += getStatScore(pokemon);

        return finalScore;
    }

    private static float getCoverageScore(Pokemon[] pokemon) {
        HashSet<String> offensiveCoverage = new HashSet<>();
        HashSet<String> defensiveCoverage = new HashSet<>();
        for (Pokemon value : pokemon) {
            if (value != null) {
                offensiveCoverage.addAll(value.getStrengths());
                defensiveCoverage.addAll(value.getResistances());
            }
        }
        return (defensiveCoverage.size() + offensiveCoverage.size());
    }

    private static float getStatScore(Pokemon[] pokemon) {
        float result = 0;
        for (Pokemon value : pokemon) {
            if (value != null) {
                ArrayList<Integer> stats = value.getStats();
                float sumStats = 0;
                for (int stat : stats) {
                    sumStats += stat;
                }
                if (sumStats <= 399) {
                    result += 5;
                } else if (sumStats > 600) {
                    result += 10;
                } else {
                    result += 6 + (int) (sumStats - 400) / 50;
                }
            }
        }
        return result;
    }
}
