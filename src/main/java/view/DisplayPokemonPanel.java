package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jetbrains.annotations.NotNull;

import entity.Move;
import entity.Pokemon;

public class DisplayPokemonPanel extends JPanel {

    public static final int THREEH = 300;
    public static final int TWOH = 200;
    public static final int THIRTY = 30;
    public static final int TWENTY = 20;
    public static final int FIVE = 5;
    public static final int FIFTEEN = 15;
    public static final int FOURSEVENFIVE = 475;
    public static final int ONESIXTY = 160;
    public static final int EIGHTY = 80;
    public static final int FOURTEEN = 14;
    public static final int EIGHT = 8;
    public static final int TEN = 10;
    private final JLabel spriteLabel = new JLabel();
    private final JPanel pokemonInfo = new JPanel();

    private static class BarChartPanel extends JPanel {

        private final java.util.List<Integer> stats;
        private final String[] labels = {
                "HP", "Atk", "Def", "SpA", "SpD", "Spe"
        };

        private final Color[] statColors = {
                new Color(230, EIGHTY, EIGHTY),
                new Color(255, 150, 60),
                new Color(240, 220, 90),
                new Color(100, 149, 255),
                new Color(120, TWOH, 120),
                new Color(255, 0, 255)
        };

        BarChartPanel(java.util.List<Integer> stats) {
            this.stats = stats;
            setPreferredSize(new Dimension(THREEH, TWOH));
            setMinimumSize(new Dimension(THREEH, TWOH));
            setMaximumSize(new Dimension(THREEH, TWOH));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            final int width = getWidth();
            final int height = getHeight() - THIRTY;

            final int maxStat = stats.stream().max(Integer::compareTo).orElse(1);
            final int barWidth = width / stats.size();

            for (int i = 0; i < stats.size(); i++) {
                final int val = stats.get(i);
                final int barHeight = (int) ((val / (double) maxStat) * (height - TWENTY));

                final int x = i * barWidth + TEN;
                final int y = height - barHeight;

                // 🟥 Stat-colored bar
                g2.setColor(statColors[i]);
                g2.fillRect(x, y, barWidth - TWENTY, barHeight);

                // Border for visibility
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, barWidth - TWENTY, barHeight);

                // Value above bar
                g2.drawString(String.valueOf(val), x + FIVE, y - FIVE);

                // Stat label
                g2.drawString(labels[i], x + FIVE, height + FIFTEEN);
            }
        }
    }

    DisplayPokemonPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        pokemonInfo.setLayout(new BoxLayout(pokemonInfo, BoxLayout.Y_AXIS));
        this.add(spriteLabel);
        this.add(pokemonInfo);
    }
    public void setPokemon(Pokemon pokemon, int iconWidth, int iconHeight) {
        try {
            final URL spriteUrl = new URL(pokemon.getSprite());
            final Image sprite = (new ImageIcon(spriteUrl).getImage()).
                    getScaledInstance(FOURSEVENFIVE, FOURSEVENFIVE, Image.SCALE_DEFAULT);

            spriteLabel.setIcon(new ImageIcon(sprite));
            spriteLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            final JPanel pokemonBasicInfo = getPokemonBasicInfo(pokemon);
            final JPanel pokemonStatsInfo = getPokemonStatsInfo(pokemon);

            this.pokemonInfo.removeAll();
            this.pokemonInfo.add(pokemonBasicInfo);
            this.pokemonInfo.add(pokemonStatsInfo);
            this.pokemonInfo.revalidate();
            this.pokemonInfo.repaint();

        }
        catch (MalformedURLException exceptione) {
            throw new RuntimeException(exceptione);
        }
        catch (NullPointerException exceptione) {
            clearPokemon();
        }
    }

    public void clearPokemon() {
        this.spriteLabel.setIcon(null);
        this.pokemonInfo.removeAll();
        this.pokemonInfo.revalidate();
        this.pokemonInfo.repaint();
    }

    @NotNull
    private static JPanel getPokemonBasicInfo(Pokemon pokemon) throws MalformedURLException {
        final JPanel basicPokemonInfo = new JPanel();
        basicPokemonInfo.setLayout(new BoxLayout(basicPokemonInfo, BoxLayout.Y_AXIS));
    //    basicPokemonInfo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        final String name = pokemon.getProperName();
        basicPokemonInfo.add(new JLabel("Name: " + name));
        final URL sprite1Url = new URL(pokemon.getType1().getSprite());
        final JLabel type1Label = new JLabel();
        final Image sprite1 = (new ImageIcon(sprite1Url).getImage()).getScaledInstance(50, TWENTY,
                Image.SCALE_DEFAULT);
        type1Label.setIcon(new ImageIcon(sprite1));
        final JPanel Type1Panel = new JPanel();
        Type1Panel.setLayout(new BoxLayout(Type1Panel, BoxLayout.X_AXIS));
        Type1Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        type1Label.setAlignmentX(Component.LEFT_ALIGNMENT);
        Type1Panel.add(type1Label);
        basicPokemonInfo.add(Type1Panel);
      //  basicPokemonInfo.add(new JLabel("Type 1: " + pokemon.getType1().toProperName()));
        if (pokemon.getType2() != null) {
            URL sprite2URL = new URL(pokemon.getType2().getSprite());
            JLabel type2Label = new JLabel();
            Image sprite2 = (new ImageIcon(sprite2URL).getImage()).getScaledInstance(50, TWENTY,
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
        scrollPane.setPreferredSize(new Dimension(ONESIXTY, EIGHTY));
        scrollPane.setMaximumSize(new Dimension(ONESIXTY, EIGHTY));  // <-- IMPORTANT
        scrollPane.setMinimumSize(new Dimension(ONESIXTY, EIGHTY));  // <-- IMPORTANT

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
        title.setFont(new Font("Arial", Font.BOLD, FOURTEEN));

        statsPanel.add(title);
        statsPanel.add(Box.createRigidArea(new Dimension(0, EIGHT)));

        BarChartPanel chart = new BarChartPanel(stats);
        statsPanel.add(chart);

        return statsPanel;
    }
}
