package view;

import interfaceadapter.main_menu.MainMenuController;
import interfaceadapter.main_menu.MainViewModel;

import javax.swing.*;
import java.awt.*;


public class HomePageView extends JPanel{
    private MainMenuController controller;
    private String viewname;
    public HomePageView(MainViewModel viewModel) {

        JPanel home = new JPanel();
        home.setLayout(new GridLayout(2, 1, 20,20));

        final JButton pokedexButton = new JButton("Pokedex");
        final JButton teamsButton = new JButton("Teams");
        final ImageIcon icon = new ImageIcon("src/main/resources/metagross.png");
        final JLabel title = new JLabel(icon);

        viewname = viewModel.getViewName();

        home.add(title);
        home.add(pokedexButton);
        home.add(teamsButton);

        this.add(home, BorderLayout.CENTER);

        pokedexButton.addActionListener(
                e -> controller.switchToPokedex());
        teamsButton.addActionListener(
                e -> controller.switchToTeams()
        );

    }
    public void setController(MainMenuController controller) {
        this.controller = controller;
    }


    public String getViewName() {
        return viewname;
    }
}
