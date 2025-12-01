package view;

import javax.swing.*;
import java.util.List;

public class DisplayFilterJList extends JList<String> {

    private final DefaultListModel<String> model;

    public DisplayFilterJList() {
        this.model = new DefaultListModel<>();
        setModel(this.model);
    }

    public void setPokemonList(List<String> pokemonList) {
        model.clear();
        for (String name : pokemonList) {
            model.addElement(name);
        }
    }
}