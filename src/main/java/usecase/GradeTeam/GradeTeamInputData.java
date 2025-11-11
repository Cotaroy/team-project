package usecase.GradeTeam;

import entity.GradingStrategy;

public class GradeTeamInputData {

    private final String teamName;
    private final GradingStrategy strategy;

    public GradeTeamInputData(String teamName, GradingStrategy strategy) {
        this.teamName = teamName;
        this.strategy = strategy;
    }

    public String getTeamName() {
        return teamName;
    }

    public GradingStrategy getStrategy() {
        return strategy;
    }
}
