package entity;

public interface GradingStrategy {
    /**
     * Some function that grades a team based on the Pokemon inside it.
     * @param team the team to be graded
     * @return the score of the team
     */
    float execute(Team team);
}
