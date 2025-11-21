package view;

import entity.Move;
import entity.Pokemon;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayPokemonJPanel extends JPanel {

    private final JLabel spriteLabel = new JLabel();
    private final JPanel pokemonInfo = new JPanel();

    private static class BarChartPanel extends JPanel {

        private final java.util.List<Integer> stats;
        private final String[] labels = {
                "HP", "Atk", "Def", "SpA", "SpD", "Spe"
        };

        private final Color[] statColors = {
                new Color(230, 80, 80),   // HP - red
                new Color(255, 150, 60),  // Attack - orange
                new Color(240, 220, 90),  // Defense - yellow
                new Color(100, 149, 255), // SpA - blue
                new Color(120, 200, 120), // SpD - green
                new Color(255, 0, 255)  // Speed - pink
        };

        public BarChartPanel(java.util.List<Integer> stats) {
            this.stats = stats;
            setPreferredSize(new Dimension(300, 200));
            setMinimumSize(new Dimension(300, 200));
            setMaximumSize(new Dimension(300, 200));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight() - 30; // label area

            int maxStat = stats.stream().max(Integer::compareTo).orElse(1);
            int barWidth = width / stats.size();

            for (int i = 0; i < stats.size(); i++) {
                int val = stats.get(i);
                int barHeight = (int) ((val / (double) maxStat) * (height - 20));

                int x = i * barWidth + 10;
                int y = height - barHeight;

                // 🟥 Stat-colored bar
                g2.setColor(statColors[i]);
                g2.fillRect(x, y, barWidth - 20, barHeight);

                // Border for visibility
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, barWidth - 20, barHeight);

                // Value above bar
                g2.drawString(String.valueOf(val), x + 5, y - 5);

                // Stat label
                g2.drawString(labels[i], x + 5, height + 15);
            }
        }
    }


    DisplayPokemonJPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        pokemonInfo.setLayout(new BoxLayout(pokemonInfo, BoxLayout.Y_AXIS));
        this.add(spriteLabel);
        this.add(pokemonInfo);
    }
    public void setPokemon(Pokemon pokemon) {
        try {
            URL spriteURL = new URL(pokemon.getSprite());
            Image sprite = (new ImageIcon(spriteURL).getImage()).getScaledInstance(475, 475, Image.SCALE_DEFAULT);

            spriteLabel.setIcon(new ImageIcon(sprite));
            spriteLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JPanel pokemonBasicInfo = getPokemonBasicInfo(pokemon);
            JPanel pokemonStatsInfo = getPokemonStatsInfo(pokemon);

            this.pokemonInfo.removeAll();
            this.pokemonInfo.add(pokemonBasicInfo);
            this.pokemonInfo.add(pokemonStatsInfo);
            this.pokemonInfo.revalidate();
            this.pokemonInfo.repaint();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static JPanel getPokemonBasicInfo(Pokemon pokemon) throws MalformedURLException {
        JPanel basicPokemonInfo = new JPanel();
        basicPokemonInfo.setLayout(new BoxLayout(basicPokemonInfo, BoxLayout.Y_AXIS));
    //    basicPokemonInfo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        String name = pokemon.getProperName();
        basicPokemonInfo.add(new JLabel("Name: " + name));
        URL sprite1URL = new URL(pokemon.getType1().getSprite());
        JLabel type1Label = new JLabel();
        Image sprite1 = (new ImageIcon(sprite1URL).getImage()).getScaledInstance(50, 20,
                Image.SCALE_DEFAULT);
        type1Label.setIcon(new ImageIcon(sprite1));
        JPanel Type1Panel = new JPanel();
        Type1Panel.setLayout(new BoxLayout(Type1Panel, BoxLayout.X_AXIS));
        Type1Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        type1Label.setAlignmentX(Component.LEFT_ALIGNMENT);
        Type1Panel.add(type1Label);
        basicPokemonInfo.add(Type1Panel);
      //  basicPokemonInfo.add(new JLabel("Type 1: " + pokemon.getType1().toProperName()));
        if (pokemon.getType2() != null) {
            URL sprite2URL = new URL(pokemon.getType2().getSprite());
            JLabel type2Label = new JLabel();
            Image sprite2 = (new ImageIcon(sprite2URL).getImage()).getScaledInstance(50, 20,
                    Image.SCALE_DEFAULT);
            type2Label.setIcon(new ImageIcon(sprite2));
            JPanel Type2Panel = new JPanel();
            Type2Panel.setLayout(new BoxLayout(Type2Panel, BoxLayout.X_AXIS));
            Type2Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

            type2Label.setAlignmentX(Component.LEFT_ALIGNMENT);
            Type2Panel.add(type2Label);
            basicPokemonInfo.add(Type2Panel);
          //  basicPokemonInfo.add(new JLabel("Type 2: " + pokemon.getType2().toProperName()));
        }
        basicPokemonInfo.add(new JLabel("Abilities:"));
        basicPokemonInfo.add(new JLabel(pokemon.getAbilities().get(0).getProperName()));
        if (pokemon.getAbilities().size() == 2) {
            basicPokemonInfo.add(new JLabel(pokemon.getAbilities().get(1).getProperName()));
        }
        if (pokemon.getHidden() != null) {
            basicPokemonInfo.add(new JLabel("Hidden Ability:"));
            basicPokemonInfo.add(new JLabel(pokemon.getHidden().getProperName()));
        }
        JList<String> moveList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> moves = new ArrayList<>();
        for (Move move : pokemon.getMoves()) {
            if (move != null) {
                moves.add(move.capitalize());
            }
            else continue;
        }

// Sort alphabetically
        Collections.sort(moves);

// Add to the model
        for (String m : moves) {
            listModel.addElement(m);
        }


        moveList.setModel(listModel);

// make it non-selectable
        moveList.setEnabled(false);

// shrink the visible size
        moveList.setVisibleRowCount(1); // small height

        JScrollPane scrollPane = new JScrollPane(moveList);
        scrollPane.setPreferredSize(new Dimension(160, 80));
        scrollPane.setMaximumSize(new Dimension(160, 80));  // <-- IMPORTANT
        scrollPane.setMinimumSize(new Dimension(160, 80));  // <-- IMPORTANT

        JPanel MovePanel = new JPanel();
        MovePanel.setLayout(new BoxLayout(MovePanel, BoxLayout.X_AXIS));
        MovePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        MovePanel.add(new JLabel("Moves: "));
        MovePanel.add(scrollPane);

        basicPokemonInfo.add(MovePanel);
        return basicPokemonInfo;
    }

    @NotNull
    private static JPanel getPokemonStatsInfo(Pokemon pokemon) {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ArrayList<Integer> stats = pokemon.getStats();

        JLabel title = new JLabel("Base Stats");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 14));

        statsPanel.add(title);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        BarChartPanel chart = new BarChartPanel(stats);
        statsPanel.add(chart);

        return statsPanel;
    }
}
