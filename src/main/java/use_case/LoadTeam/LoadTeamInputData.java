package use_case.LoadTeam;

public class LoadTeamInputData {
    private final String teamName;

    public LoadTeamInputData(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamName() {
        return teamName;
    }
}
