package view;

import entity.Pokemon;
import interface_adapter.pokemon_lookup.PokemonLookupController;
import interface_adapter.pokemon_lookup.PokemonLookupState;
import interface_adapter.pokemon_lookup.PokemonLookupViewModel;
import usecase.PokemonLookup.PokemonLookupInputBoundary;
import usecase.filter.FilterPokemonDataAccess;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class PokemonLookupView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final PokemonLookupViewModel pokemonLookupViewModel;

    private final JTextField pokemonNameInputField = new JTextField(15);
    private PokemonLookupController pokemonLookupController = null;

    private final JTextField filterTypeInputField = new JTextField(15);
    private final JTextField filterValueInputField = new JTextField(15);

    private final JButton search;
    private final JButton saveToTeam;
    private final JButton filterButton;
    private final DisplayPokemonJPanel displayPokemon = new DisplayPokemonJPanel();
    private final DisplayFilterJList displayFilter = new DisplayFilterJList();

    public PokemonLookupView(PokemonLookupViewModel pokemonLookupViewModel) {
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.pokemonLookupViewModel.addPropertyChangeListener(this);

        this.viewName = pokemonLookupViewModel.getViewName();

        final JLabel title = new JLabel(PokemonLookupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel pokemonNameInfo = new LabelTextPanel(
                new JLabel(PokemonLookupViewModel.POKEMON_NAME_LABEL), pokemonNameInputField);

        final JPanel buttons = new JPanel();
        search = new JButton(PokemonLookupViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        saveToTeam = new JButton(PokemonLookupViewModel.SAVE_TO_TEAM_LABEL);
        buttons.add(saveToTeam);

        pokemonNameInfo.add(buttons);

        //Making filter panel
        final JPanel filterInfo = new JPanel();
        filterInfo.add(new JLabel("Filter by:"));
        filterInfo.add(filterTypeInputField);
        filterInfo.add(new JLabel("Value:"));
        filterInfo.add(filterValueInputField);
        filterButton = new JButton("Filter");
        filterInfo.add(filterButton);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            updatePokemonDisplay(pokemonLookupViewModel);
                        }
                    }
                }
        );

        saveToTeam.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveToTeam)) {
                            PokemonLookupState currentState = pokemonLookupViewModel.getState();
                            Pokemon currentPokemon = currentState.getDisplayPokemon();
                            if (currentPokemon != null) {
                                pokemonLookupController.switchToTeamBuilderView(currentState.getIndex(), currentPokemon);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "No pokemon to add.");
                            }
                        }
                    }
                }
        );

        pokemonNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent evt) {
                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            updatePokemonDisplay(pokemonLookupViewModel);
                        }

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        filterButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(filterButton)) {

                            String filterType = filterTypeInputField.getText();
                            String filterValue = filterValueInputField.getText();
                            FilterPokemonDataAccess dataAccess =  new FilterPokemonDataAccess();

                            if(dataAccess.filterTargetExists(filterType, filterValue)) {
                                displayFilter.setPokemonList(dataAccess.getPokemonByFilter(filterType, filterValue));
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Not a valid Filter");
                            }
                        }

                    }
                }

        );

        addPokemonNameListener();
        addFilterTypeListener();
        addFilterValueListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(pokemonNameInfo);
        this.add(filterInfo);
        this.add(displayPokemon);
        this.add(displayFilter);
    }

    private void updatePokemonDisplay(PokemonLookupViewModel pokemonLookupViewModel) {
        final PokemonLookupState currentState = pokemonLookupViewModel.getState();
        try {
            pokemonLookupController.execute(currentState.getPokemonName());
            displayPokemon.setPokemon(currentState.getDisplayPokemon(), 384, 384);

        } catch (IOException | PokemonLookupInputBoundary.PokemonNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Not a valid Pokemon Name");
        }
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

    private void addFilterTypeListener() {
        filterTypeInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                currentState.setFilterType(filterTypeInputField.getText());
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

    private void addFilterValueListener() {
        filterValueInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                currentState.setFilterValue(filterValueInputField.getText());
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
