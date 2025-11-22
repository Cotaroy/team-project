package usecase.filter;

import java.util.List;

public class FilterPokemonInteractor implements FilterPokemonInputBoundary {
    private final FilterPokemonDataAccessInterface dataAccess;
    private final FilterPokemonOutputBoundary presenter;

    public FilterPokemonInteractor(FilterPokemonDataAccessInterface dataAccess, FilterPokemonOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(FilterPokemonInputData inputData) {
        String category = inputData.getFilterCategory();
        String value = inputData.getFilterValue();
//for blank input
        if (category == null || category.isBlank() || value == null || value.isBlank()) {
            presenter.prepareFailView("Filter category and value must not be blank.");
            return;
        }

        category = category.trim().toLowerCase();
        value = value.trim().toLowerCase();
//for invalid input(category and value)
        if (!category.equals("type") && !category.equals("ability") && !category.equals("egg-group") && !category.equals("move")) {
            presenter.prepareFailView("Invalid filter category.");
        } else if (!dataAccess.filterTargetExists(category, value)) {
            presenter.prepareFailView("Filter value does not exist.");
        } else {
            List<String> results = dataAccess.getPokemonByFilter(category, value);
            FilterPokemonOutputData outputData = new FilterPokemonOutputData(category, value, results);
            presenter.prepareSuccessView(outputData);
            }
        }
    }
