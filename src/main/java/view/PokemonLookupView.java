package view;

import dataaccess.AbilityMap;
import dataaccess.MoveMap;
import entity.Ability;
import entity.Move;
import entity.Pokemon;
import interfaceadapter.pokemonlookup.PokemonLookupController;
import interfaceadapter.pokemonlookup.PokemonLookupState;
import interfaceadapter.pokemonlookup.PokemonLookupViewModel;
import usecase.filter.FilterPokemonDataAccess;
import usecase.filter.FilterPokemonDataAccessInterface;
import usecase.lookup.PokemonLookupDataAccessInterface;
import usecase.lookup.PokemonLookupInputBoundary;

import java.awt.event.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Collections;
import java.util.Map;

public class PokemonLookupView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final PokemonLookupViewModel pokemonLookupViewModel;

    private final JTextField pokemonNameInputField = new JTextField(15);
    private PokemonLookupController pokemonLookupController = null;

    private final JComboBox<String> filterTypeDropdown = new JComboBox<>(PokemonLookupViewModel.FILTERS);

    private final DefaultListModel<String> filterValueModel = new DefaultListModel<>();
    private final JList<String> filterValueList = new JList<>(filterValueModel);
    private final JScrollPane filterValueDropdown = new JScrollPane(filterValueList);

    private final JButton search;
    private final JButton saveToTeam;
    private final JButton filterButton;
    private final DisplayPokemonPanel displayPokemon = new DisplayPokemonPanel();

    private final DefaultListModel<String> filteredListModel =  new DefaultListModel<>();
    private final JList<String> filteredList = new JList<>(filteredListModel);
    private final JScrollPane displayFilter = new JScrollPane(filteredList);

    public PokemonLookupView(PokemonLookupViewModel pokemonLookupViewModel) {
        this.pokemonLookupViewModel = pokemonLookupViewModel;
        this.pokemonLookupViewModel.addPropertyChangeListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.width -= 10;
        screenSize.height -= 200;
        this.setPreferredSize(screenSize);

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

        final JPanel filterInfo = new JPanel();
        filterInfo.add(new JLabel(PokemonLookupViewModel.FILTER_BY_LABEL));
        filterInfo.add(filterTypeDropdown);
        filterInfo.add(new JLabel(PokemonLookupViewModel.FILTER_VALUE_LABEL));
        filterInfo.add(filterValueDropdown);
        filterButton = new JButton(PokemonLookupViewModel.FILTER_BUTTON_LABEL);
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

        filterTypeDropdown.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(filterTypeDropdown)) {
                            String selectedType = filterTypeDropdown.getSelectedItem().toString();
                            setFilterValues(selectedType);
                        }

                    }
                }

        );

        filterButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(filterButton)) {
                            if (filterTypeDropdown.getSelectedIndex() != -1 && filterValueList.getSelectedIndex() != -1) {
                                updateFilterDisplay(pokemonLookupViewModel);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Please select a filter");
                            }
                        }

                    }
                }

        );

        filteredList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click
                    int index = filteredList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedPokemonName = filteredListModel.getElementAt(index);
                        if (selectedPokemonName != null) {
                            // Set the selected Pokémon name in the input field and state
                            pokemonNameInputField.setText(selectedPokemonName);

                            // Update the state
                            final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                            currentState.setPokemonName(selectedPokemonName);
                            pokemonLookupViewModel.setState(currentState);

                            // Trigger the search/display for this Pokémon
                            updatePokemonDisplay(pokemonLookupViewModel);
                        }
                    }
                }
            }
        });

        addPokemonNameListener();
        addFilterTypeListener();
        addFilterValueListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(pokemonNameInfo);
        topPanel.add(filterInfo);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(displayPokemon,  BorderLayout.WEST);
        bottomPanel.add(displayFilter,  BorderLayout.EAST);

        this.add(title);
        this.add(topPanel);
        this.add(bottomPanel);
    }

    private void updatePokemonDisplay(PokemonLookupViewModel pokemonLookupViewModel) {
        final PokemonLookupState currentState = pokemonLookupViewModel.getState();
        try {
            pokemonLookupController.execute(currentState.getPokemonName());
            displayPokemon.setPokemon(currentState.getDisplayPokemon(), 384, 384);

        }
        catch (IOException | PokemonLookupDataAccessInterface.PokemonNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Not a valid Pokemon Name");
        }
    }

    private void updateFilterDisplay(PokemonLookupViewModel pokemonLookupViewModel) {
        final PokemonLookupState currentState = pokemonLookupViewModel.getState();
        try {
            pokemonLookupController.setFilterDisplay(currentState.getFilterType(), currentState.getFilterValue());
            filteredListModel.clear();
            for (String s : currentState.getFilteredPokemonList()){
                filteredListModel.addElement(s);
            }
            displayFilter.setViewportView(filteredList);
            displayFilter.revalidate();
            displayFilter.repaint();
        }
        catch (IOException e) {JOptionPane.showMessageDialog(null, "Failed to filter");
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
        filterTypeDropdown.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(filterTypeDropdown)) {
                            String selectedType = filterTypeDropdown.getSelectedItem().toString();
                            setFilterValues(selectedType);

                            // Update state
                            final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                            currentState.setFilterType(selectedType);
                            currentState.setFilterValue(""); // Clear filter value when type changes
                            pokemonLookupViewModel.setState(currentState);
                        }
                    }
                }
        );
    }

    private void addFilterValueListener() {
        filterValueList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = filterValueList.getSelectedValue();
                    if (selectedValue != null) {
                        // Update state
                        final PokemonLookupState currentState = pokemonLookupViewModel.getState();
                        currentState.setFilterValue(selectedValue);
                        pokemonLookupViewModel.setState(currentState);
                    }
                }
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
        if ("state".equals(evt.getPropertyName())) {
            final PokemonLookupState currentState = (PokemonLookupState) evt.getNewValue();
            if (currentState.getPokemonNameError() != null) {
                if (currentState.getPokemonNameError().contains("not found")) {
                    JOptionPane.showMessageDialog(null, "Not a valid Pokemon Name");
                    currentState.setPokemonNameError(null);
                }
                else if ("No Pokemon name provided.".equals(currentState.getPokemonNameError())) {
                    JOptionPane.showMessageDialog(null, "No Pokemon name provided");
                    currentState.setPokemonNameError(null);
                }
            }
        }
    }

    public void setFilterValues(String filterType) {
        filterValueModel.clear();
        switch (filterType) {
            case "type":
                for (String s : PokemonLookupViewModel.TYPE_VALUES) {
                    filterValueModel.addElement(s);
                }
                break;
            case "ability":
                AbilityMap abilityMap = new AbilityMap();
                Map<Integer, Ability> abilities = abilityMap.getAbilities();
                for (Ability ability : abilities.values()) {
                    filterValueModel.addElement(ability.getName());
                }
                break;
            case "egg-group":
                for (String s : PokemonLookupViewModel.EGG_GROUPS) {
                    filterValueModel.addElement(s);
                }
                break;
            case "move":
                MoveMap moveMap = new MoveMap();
                Map<Integer, Move> moves = moveMap.getMoves();
                for (Move move : moves.values()) {
                    filterValueModel.addElement(move.getName());
                }
                break;
            case "pokedex":
                for (String s : PokemonLookupViewModel.REGIONS) {
                    filterValueModel.addElement(s);
                }
                break;

            default:
                break;

        }
        filterValueDropdown.setViewportView(filterValueList);
        filterValueDropdown.revalidate();
        filterValueDropdown.repaint();
        }

    }

