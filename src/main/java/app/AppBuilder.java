package app;

import entity.EmptyPokemonFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.pokemon_lookup.PokemonLookupController;
import interface_adapter.pokemon_lookup.PokemonLookupPresenter;
import interface_adapter.pokemon_lookup.PokemonLookupViewModel;
import use_case.PokemonLookup.PokemonLookupInputBoundary;
import use_case.PokemonLookup.PokemonLookupInteractor;
import use_case.PokemonLookup.PokemonLookupOutputBoundary;
import view.PokemonLookupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // this dont exist yet
    // final DataAccessObject = new blah

    private PokemonLookupView pokemonLookupView;
    private PokemonLookupViewModel pokemonLookupViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addPokemonLookupView() {
        pokemonLookupViewModel = new PokemonLookupViewModel();
        pokemonLookupView = new PokemonLookupView(pokemonLookupViewModel);
        cardPanel.add(pokemonLookupView, pokemonLookupView.getName());
        return this;
    }

    public AppBuilder addPokemonLookupUseCase() {
        final PokemonLookupOutputBoundary pokemonLookupOutputBoundary = new PokemonLookupPresenter(
                pokemonLookupViewModel, viewManagerModel);
        final PokemonLookupInputBoundary pokemonLookupInteractor =
                new PokemonLookupInteractor(pokemonLookupOutputBoundary, EmptyPokemonFactory.create());
        PokemonLookupController controller = new PokemonLookupController(pokemonLookupInteractor);
        pokemonLookupView.setPokemonLookupController(controller);
        return this;
    }

    public JFrame build() {
        JFrame application = new JFrame("MetaGross");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(pokemonLookupView.getViewName());

        return application;
    }
}
