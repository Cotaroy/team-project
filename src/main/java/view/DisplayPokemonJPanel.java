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
        Type1Panel.add(new JLabel("Type: "));
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
            Type2Panel.add(new JLabel("Type: "));
            Type2Panel.add(type2Label);
            basicPokemonInfo.add(Type2Panel);
          //  basicPokemonInfo.add(new JLabel("Type 2: " + pokemon.getType2().toProperName()));
        }
        basicPokemonInfo.add(new JLabel("Abilities: " + pokemon.getAbilities().get(0).getProperName()));
        if (pokemon.getAbilities().size() == 2) {
            basicPokemonInfo.add(new JLabel(pokemon.getAbilities().get(1).getProperName()));
        }
        if (pokemon.getHidden() != null) {
            basicPokemonInfo.add(new JLabel("Hidden Ability: " + pokemon.getHidden().getProperName()));
        }
        JList<String> moveList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> moves = new ArrayList<>();
        for (Move move : pokemon.getMoves()) {
            moves.add(move.capitalize());
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
        MovePanel.add(new JLabel("Moves: "));
        MovePanel.add(scrollPane);

        basicPokemonInfo.add(MovePanel);
        return basicPokemonInfo;
    }

    @NotNull
    private static JPanel getPokemonStatsInfo(Pokemon pokemon) {
        JPanel pokemonStatsInfo = new JPanel();
        pokemonStatsInfo.setLayout(new BoxLayout(pokemonStatsInfo, BoxLayout.Y_AXIS));
        pokemonStatsInfo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        ArrayList<Integer> stats = pokemon.getStats();
        pokemonStatsInfo.add(new JLabel("HP: " + stats.get(0)));
        pokemonStatsInfo.add(new JLabel("Attack: " + stats.get(1)));
        pokemonStatsInfo.add(new JLabel("Defense: " + stats.get(2)));
        pokemonStatsInfo.add(new JLabel("Special-Attack: " + stats.get(3)));
        pokemonStatsInfo.add(new JLabel("Special-Defense: " + stats.get(4)));
        pokemonStatsInfo.add(new JLabel("Speed: " + stats.get(5)));
        return pokemonStatsInfo;
    }
}
