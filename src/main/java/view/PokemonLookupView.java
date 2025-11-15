package view;

import interface_adapter.pokemon.PokemonLookupController;
import interface_adapter.pokemon.PokemonLookupState;
import interface_adapter.pokemon.PokemonLookupViewModel;
import use_case.PokemonLookup.PokemonLookupInputBoundary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class PokemonLookupView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "PokemonLookupView";
    private final PokemonLookupViewModel pokemonLookupViewModel;

    private final JTextField pokemonNameInputField = new JTextField(15);
    private PokemonLookupController pokemonLookupController = null;

    private final JButton search;
    private final JTextArea displayPokemon = new JTextArea();

    public PokemonLookupView(PokemonLookupViewModel pokemonLookupViewModel) {
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.pokemonLookupViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(PokemonLookupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel pokemonNameInfo = new LabelTextPanel(
                new JLabel(PokemonLookupViewModel.POKEMON_NAME_LABEL), pokemonNameInputField);

        final JPanel buttons = new JPanel();
        search = new JButton(PokemonLookupViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            final PokemonLookupState currentState = pokemonLookupViewModel.getState();

                            try {
                                pokemonLookupController.execute(currentState.getPokemonName());
                                displayPokemon.setText(currentState.getDisplayPokemon().toString());
                            }
                            catch (IOException | PokemonLookupInputBoundary.PokemonNotFoundException e) {
                                JOptionPane.showMessageDialog(null, "Not a valid Pokemon Name");
                            }
                        }
                    }
                }
        );

        addPokemonNameListener();

        displayPokemon.setEditable(false);
        displayPokemon.setLineWrap(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(pokemonNameInfo);
        this.add(buttons);
        this.add(displayPokemon);
    }

    private void addPokemonNameListener() {
        pokemonNameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                currentState.setPokemonName(pokemonNameInputField.getText());
                pokemonLookupViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    public void setPokemonLookupController(PokemonLookupController pokemonLookupController) {
        this.pokemonLookupController = pokemonLookupController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
