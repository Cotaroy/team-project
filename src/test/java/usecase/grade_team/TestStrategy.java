package usecase.grade_team;

import entity.GradingStrategy;
import entity.Team;

public class TestStrategy implements GradingStrategy {

    @Override
    public float execute(Team team) {
        float score = 0;
        for(int i = 0; i < 6; i++){
            if(team.getPokemon(i) != null) score++;
        }
        return score;
    }
}
