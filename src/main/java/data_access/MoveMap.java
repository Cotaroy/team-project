package data_access;

import entity.Move;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MoveMap {
    private final Map<String, Move> moves = new HashMap<>();

    public MoveMap() {
        this("moves.json");
    }

    public Move getMove(int id) {
        return moves.get(Integer.toString(id));
    }

    public MoveMap(String filename) {
        try {
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject moveData = jsonArray.getJSONObject(i);
                String id = moveData.getString("id");

                String name = moveData.getString("name");
                int power = 0;
                try {
                    power =  moveData.getInt("power");
                } catch (NullPointerException e) {
                    power = 0;
                }
                int accuracy = 0;
                try {
                    accuracy =  moveData.getInt("power");
                } catch (NullPointerException e) {
                    accuracy = 0;
                }

                String effect = moveData.getJSONObject("effect_entries").getString("effect");

                String url = moveData.getJSONObject("type").getString("url");
                int type_id = url.charAt(url.length() - 2);

                int damage = 0;

                Move move = new Move(name, power, accuracy, effect, type_id, damage);

                moves.put(id, move);

            }

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
