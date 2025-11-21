package use_case.RegionPokedex;

import entity.EmptyPokemonFactory;
import entity.Pokemon;
import org.junit.jupiter.api.Test;
import use_case.seeRegionPokedex.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TestRegionPokedex {

    @Test
    void correctLengthTest() throws IOException {

        RegionPokedexOutputBoundary presenter = new RegionPokedexOutputBoundary() {
            @Override
            public void prepareSuccessView(RegionPokedexOutputData data) {
                assertEquals(151, data.getPokemonNames().size());
            }

            @Override
            public void prepareFailureView(String error) {
                fail("Should not fail: " + error);
            }
        };

        RegionPokedexDataAccessInterface dataAccess = new RegionPokedexDataAccess();
        RegionPokedexInteractor interactor = new RegionPokedexInteractor(dataAccess, presenter);

        RegionPokedexInputData input = new RegionPokedexInputData("kanto");
        interactor.execute(input);
    }


}
