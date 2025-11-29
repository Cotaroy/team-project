package interface_adapter.main_menu;

import interface_adapter.ViewModel;

public class MainViewModel extends ViewModel<MainViewState> {

    // ====== UI Label Text ======
    public static final String TITLE_LABEL = "Main Menu";
    public static final String POKEDEX_BUTTON_LABEL = "Pokedex";
    public static final String TEAMS_BUTTON_LABEL = "Teams";

    public MainViewModel() {
        super("main menu");
        setState(new MainViewState());
    }
}
