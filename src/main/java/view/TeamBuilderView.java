package view;

import entity.Pokemon;
import entity.Team;
import interface_adapter.team_builder.TeamBuilderController;
import interface_adapter.team_builder.TeamBuilderState;
import interface_adapter.team_builder.TeamBuilderViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class TeamBuilderView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final TeamBuilderViewModel teamBuilderViewModel;

    private final JTextField teamNameInputField = new JTextField(15);
    private TeamBuilderController teamBuilderController = null;

    final JPanel teamDisplayPanel = new JPanel();

    private final JButton saveButton;

    private final JPanel pokemonActionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
    private final JButton buttonAdd = new JButton("Add");
    private final JButton buttonRemove = new JButton("Remove");
    public JPanel[] teamSlots = new JPanel[6];
    private int currentSlotIndex = -1;
    public TeamBuilderView(TeamBuilderViewModel teamBuilderViewModel) {

        this.teamBuilderViewModel = teamBuilderViewModel;
        this.teamBuilderViewModel.addPropertyChangeListener(this);
        this.viewName = teamBuilderViewModel.getViewName();
        final JLabel title = new JLabel(TeamBuilderViewModel.TITLE_LABEL);
        title.setAlignmentX(CENTER_ALIGNMENT);
        teamNameInputField.setText(teamBuilderViewModel.getState().getTeam().getTeamName());
        final LabelTextPanel teamNameInfo = new LabelTextPanel(
                new JLabel(TeamBuilderViewModel.TEAM_NAME_LABEL), teamNameInputField);
        teamDisplayPanel.setLayout(new GridLayout(3, 2, 5, 5));
        pokemonActionPanel.add(buttonAdd);
        pokemonActionPanel.add(buttonRemove);
        buttonAdd.setVisible(false);
        buttonRemove.setVisible(false);

        JPanel teamSlot0 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot0);
        JPanel teamSlot1 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot1);
        JPanel teamSlot2 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot2);
        JPanel teamSlot3 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot3);
        JPanel teamSlot4 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot4);
        JPanel teamSlot5 = new DisplayPokemonInTeamJPanel();
        teamDisplayPanel.add(teamSlot5);

        teamSlots[0] = teamSlot0;
        teamSlots[1] = teamSlot1;
        teamSlots[2] = teamSlot2;
        teamSlots[3] = teamSlot3;
        teamSlots[4] = teamSlot4;
        teamSlots[5] = teamSlot5;

        setTeamSlotBorders();
        addTeamSlotMouseListeners();
        updateSlotDisplays();

        final JPanel buttons = new JPanel();
        saveButton = new JButton(TeamBuilderViewModel.SAVE_BUTTON_LABEL);
        buttons.add(saveButton);

        saveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveButton)) {
                            final TeamBuilderState currentState = teamBuilderViewModel.getState();
                            try {
                                teamBuilderController.saveTeam(currentState.getTeam());
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error saving team");
                            }

                        }
                    }
                }
        );
        buttonAdd.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        TeamBuilderState currentState = teamBuilderViewModel.getState();
                        try {
                            teamBuilderController.removeFromTeam("", currentState.getTeam(), currentSlotIndex);
                            teamBuilderController.switchToPokemonLookupView(currentSlotIndex);
                            buttonAdd.setVisible(false);
                            buttonRemove.setVisible(false);
                            updateSlotDisplays();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        buttonRemove.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        TeamBuilderState currentState = teamBuilderViewModel.getState();
                        try {
                            teamBuilderController.removeFromTeam("", currentState.getTeam(), currentSlotIndex);
                            buttonAdd.setVisible(false);
                            buttonRemove.setVisible(false);
                            updateSlotDisplays();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );


        addTeamNameListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(teamNameInfo);
        this.add(teamDisplayPanel);
        this.add(buttons);

    }

    private void updateSlotDisplays() {
        for (int i = 0; i < teamDisplayPanel.getComponentCount(); i++) {
            DisplayPokemonInTeamJPanel component = (DisplayPokemonInTeamJPanel) teamDisplayPanel.getComponent(i);
            Team team = teamBuilderViewModel.getState().getTeam();
            if (team != null) {
                component.setPokemon(team.getPokemon(i), 150, 150);
            }
        }
        teamDisplayPanel.revalidate();
        teamDisplayPanel.repaint();
    }

    private void addTeamSlotMouseListeners() {
        for (int i = 0; i < teamDisplayPanel.getComponentCount(); i++) {
            final int index = i;
            JPanel component = (JPanel) teamDisplayPanel.getComponent(i);
            component.addMouseListener(
                    new MouseListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                        }

                        @Override
                        public void mousePressed(MouseEvent evt) {
                            final TeamBuilderState currentState = teamBuilderViewModel.getState();
                            currentSlotIndex = index;
                            if (currentState.getTeam().getPokemon(index) != null){
                                    JPanel currentScreen = teamSlots[index];

//                                    currentScreen.removeAll();
                                    currentScreen.add(pokemonActionPanel);
                                    buttonAdd.setVisible(true);
                                    buttonRemove.setVisible(true);
                                    currentScreen.revalidate();
                                    currentScreen.repaint();

                                    return;
                            }

                            buttonAdd.setVisible(false);
                            buttonRemove.setVisible(false);
                            teamBuilderController.switchToPokemonLookupView(index);
                            System.out.println("Pokemon Slot " + index + " clicked");
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            component.setBorder(BorderFactory.createLineBorder(Color.red));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            component.setBorder(BorderFactory.createLineBorder(Color.black));
                        }
                    }
            );
        }
    }

    private void setTeamSlotBorders() {
        for (int i = 0; i < teamDisplayPanel.getComponentCount(); i++) {
            JPanel component = (JPanel) teamDisplayPanel.getComponent(i);
            component.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }

    private void addTeamNameListener() {
        teamNameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TeamBuilderState currentState = teamBuilderViewModel.getState();
                currentState.getTeam().setTeamName(teamNameInputField.getText());
                teamBuilderViewModel.setState(currentState);
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

    public void setTeamBuilderController(TeamBuilderController teamBuilderController) {
        this.teamBuilderController = teamBuilderController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateSlotDisplays();
    }





}
