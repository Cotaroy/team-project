package usecase.filter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFilterPokemon {

    /**
     * data access interface
     * Simulate two valid filters: "type" + "fire" and "move" + "thunderbolt".
     */
    private static class FakeFilterPokemonDataAccess implements FilterPokemonDataAccessInterface {

        @Override
        public boolean filterTargetExists(String filterCategory, String filterValue) {
            // Simulate: "type" with "fire" or "move" with "thunderbolt" exists, everything else doesn't.
            return ("type".equals(filterCategory) && "fire".equals(filterValue.toLowerCase())) ||
                   ("move".equals(filterCategory) && "thunderbolt".equals(filterValue.toLowerCase()));
        }

        @Override
        public List<String> getPokemonByFilter(String filterCategory, String filterValue) {
            if ("type".equals(filterCategory) && "fire".equals(filterValue.toLowerCase())) {
                return new ArrayList<>(Arrays.asList("charmander", "vulpix"));
            }
            if ("move".equals(filterCategory) && "thunderbolt".equals(filterValue.toLowerCase())) {
                return new ArrayList<>(Arrays.asList("pikachu", "raichu"));
            }
            return Collections.emptyList();
        }
    }

    /**
     *  presenter
     */
    private static class TestPresenter implements FilterPokemonOutputBoundary {
        FilterPokemonOutputData successModel;
        String failMessage;

        @Override
        public void prepareSuccessView(FilterPokemonOutputData responseModel) {
            this.successModel = responseModel;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.failMessage = errorMessage;
        }
    }

    @Test
    void success_whenValidTypeFire() {
        FakeFilterPokemonDataAccess dataAccess = new FakeFilterPokemonDataAccess();
        TestPresenter presenter = new TestPresenter();
        FilterPokemonInteractor interactor = new FilterPokemonInteractor(dataAccess, presenter);

        FilterPokemonInputData inputData =
                new FilterPokemonInputData("type", "fire");

        interactor.execute(inputData);

        assertNotNull(presenter.successModel);
        assertNull(presenter.failMessage);
        assertEquals("type", presenter.successModel.getFilterCategory());
        assertEquals("fire", presenter.successModel.getFilterValue());
        assertTrue(presenter.successModel.getPokemonNames().contains("charmander"));
    }

    @Test
    void success_whenValidMoveThunderbolt() {
        FakeFilterPokemonDataAccess dataAccess = new FakeFilterPokemonDataAccess();
        TestPresenter presenter = new TestPresenter();
        FilterPokemonInteractor interactor = new FilterPokemonInteractor(dataAccess, presenter);

        FilterPokemonInputData inputData =
                new FilterPokemonInputData("move", "thunderbolt");

        interactor.execute(inputData);

        assertNotNull(presenter.successModel);
        assertNull(presenter.failMessage);
        assertEquals("move", presenter.successModel.getFilterCategory());
        assertEquals("thunderbolt", presenter.successModel.getFilterValue());
        assertTrue(presenter.successModel.getPokemonNames().contains("pikachu"));
    }

    @Test
    void fail_whenCategoryInvalid() {
        FakeFilterPokemonDataAccess dataAccess = new FakeFilterPokemonDataAccess();
        TestPresenter presenter = new TestPresenter();
        FilterPokemonInteractor interactor = new FilterPokemonInteractor(dataAccess, presenter);

        FilterPokemonInputData inputData =
                new FilterPokemonInputData("wrong", "fire");

        interactor.execute(inputData);

        assertNull(presenter.successModel);
        assertNotNull(presenter.failMessage);
        assertEquals("Invalid filter category.", presenter.failMessage);
    }

    @Test
    void fail_whenValueBlank() {
        FakeFilterPokemonDataAccess dataAccess = new FakeFilterPokemonDataAccess();
        TestPresenter presenter = new TestPresenter();
        FilterPokemonInteractor interactor = new FilterPokemonInteractor(dataAccess, presenter);

        FilterPokemonInputData inputData =
                new FilterPokemonInputData("type", "  ");

        interactor.execute(inputData);

        assertNull(presenter.successModel);
        assertNotNull(presenter.failMessage);
        assertEquals("Filter category and value must not be blank.", presenter.failMessage);
    }

    @Test
    void fail_whenValueDoesNotExist() {
        FakeFilterPokemonDataAccess dataAccess = new FakeFilterPokemonDataAccess();
        TestPresenter presenter = new TestPresenter();
        FilterPokemonInteractor interactor = new FilterPokemonInteractor(dataAccess, presenter);

        FilterPokemonInputData inputData =
                new FilterPokemonInputData("type", "super-huge-dragon");

        interactor.execute(inputData);

        assertNull(presenter.successModel);
        assertNotNull(presenter.failMessage);
        assertEquals("Filter value does not exist.", presenter.failMessage);
    }
}
