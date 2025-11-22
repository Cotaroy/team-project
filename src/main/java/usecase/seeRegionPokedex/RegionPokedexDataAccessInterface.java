package usecase.seeRegionPokedex;

import java.io.IOException;
import java.util.ArrayList;

public interface RegionPokedexDataAccessInterface {
    ArrayList<String> getPokedexData(String api_exists) throws IOException;
}
