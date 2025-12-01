package app;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addHomePageView()
                .addTeamBuilderView()
                .addPokemonLookupView()

                .addMainMenuUseCase()
                .addTeamBuilderUseCase()
                .addPokemonLookupUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        application.setSize(screenSize.width, screenSize.height);
        application.setResizable(true);
    }
}
