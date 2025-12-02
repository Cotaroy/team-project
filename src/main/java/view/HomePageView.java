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
        JPanel titlePage = new JPanel();
        home.setLayout(new GridLayout(2, 1, 20,20));
        this.setLayout(new BorderLayout());
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.add(home);

        final JButton pokedexButton = new JButton("Pokedex");
        final JButton teamsButton = new JButton("Teams");
        final ImageIcon icon = new ImageIcon("src/main/resources/metagross.png");
        final JLabel title = new JLabel(icon);
        title.setPreferredSize(new Dimension(1000, 300));

        viewname = viewModel.getViewName();

        titlePage.add(title);
        home.add(pokedexButton);
        home.add(teamsButton);

        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(titlePage, BorderLayout.NORTH);

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
