package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TeamGrader implements GradingStrategy {

    static final int BASE_STAT_SCORE = 5;
    static final int MAX_STAT_SCORE = 10;
    static final int STAT_LOWER_BOUND = 399;
    static final int STAT_UPPER_BOUND = 600;
    static final int STAT_INTERVAL = 50;

    @Override
    public float execute(Team team) {
        float finalScore = 0;

        final Pokemon[] pokemon = team.getPokemon();

        finalScore += getCoverageScore(pokemon);
        finalScore += getStatScore(pokemon);

        return finalScore;
    }

    private static float getCoverageScore(Pokemon[] pokemon) {
        final Set<String> offensiveCoverage = new HashSet<>();
        final Set<String> defensiveCoverage = new HashSet<>();
        for (Pokemon value : pokemon) {
            if (value != null) {
                if (value.getType2() == null) {
                    offensiveCoverage.addAll(value.getType1().getStrengths());
                    defensiveCoverage.addAll(value.getType1().getResistances());
                }
                else {
                    offensiveCoverage.addAll(value.getStrengths());
                    defensiveCoverage.addAll(value.getResistances());
                }
            }
        }
        return defensiveCoverage.size() + offensiveCoverage.size();
    }

    private static float getStatScore(Pokemon[] pokemon) {
        float result = 0;
        for (Pokemon value : pokemon) {
            if (value != null) {
                final ArrayList<Integer> stats = value.getStats();
                float sumStats = 0;
                for (int stat : stats) {
                    sumStats += stat;
                }
                result += compareToBounds(sumStats);
            }
        }
        return result;
    }

    private static float compareToBounds(float sumStats) {
        float change = 0;
        if (sumStats <= STAT_LOWER_BOUND) {
            change += BASE_STAT_SCORE;
        }
        else if (sumStats >= STAT_UPPER_BOUND) {
            change += MAX_STAT_SCORE;
        }
        else {
            change += BASE_STAT_SCORE + 1 + (int) (sumStats - STAT_LOWER_BOUND) / STAT_INTERVAL;
        }
        return change;
    }
}
