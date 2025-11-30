package interfaceadapter.teambuilder;

import interfaceadapter.ViewModel;

public class TeamBuilderViewModel extends ViewModel<TeamBuilderState> {

    public static final String TITLE_LABEL = "Team Builder";
    public static final String TEAM_NAME_LABEL = "Team Name";
    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String LOAD_BUTTON_LABEL = "Load";
    public static final String GRADE_TEAM_BUTTON_LABEL = "Grade Team";

    public TeamBuilderViewModel() {
        super("team builder");
        setState(new TeamBuilderState());
    }
}
