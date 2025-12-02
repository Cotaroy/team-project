package usecase.load_team;

public class LoadTeamInputData {
    /**
     * Name of the team.
     */
    private final String teamName;

    /**
     * Initialize input data.
     * @param teamNameParam name of the team being loaded.
     */
    public LoadTeamInputData(final String teamNameParam) {
        this.teamName = teamNameParam;
    }

    /**
     * Get the name of the team.
     * @return name of the team.
     */
    public String getTeamName() {
        return teamName;
    }
}
