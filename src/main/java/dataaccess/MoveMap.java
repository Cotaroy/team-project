package dataaccess;

import entity.Move;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MoveMap {
    private final Map<Integer, Move> moves = new HashMap<>();

    public Move getMove(int id) {
        return moves.get(id);
    }

    public Map<Integer, Move> getMoves() {
        return moves;
    }

    public MoveMap() {
        try {
            String jsonText = new String(
                    getClass().getClassLoader().getResourceAsStream("moves.json").readAllBytes()
            );

            // Parse as JSON object
            JSONObject root = new JSONObject(jsonText);

            // Convert dictionary values to an array
            JSONArray jsonArray = new JSONArray();

            for (String key : root.keySet()) {
                jsonArray.put(root.getJSONObject(key));
            }

            for (int i = 1; i < jsonArray.length(); i++) {

                JSONObject moveData = jsonArray.getJSONObject(i);
                int id = moveData.getInt("id");

                String name = moveData.getString("name");
                String damage = moveData.getJSONObject("damage_class").getString("name");
                int power;
                try {
                    power =  moveData.getInt("power");
                } catch (JSONException e) {
                    power = 0;
                }
                int accuracy;
                try {
                    accuracy =  moveData.getInt("accuracy");
                } catch (JSONException e) {
                    accuracy = 0;
                }
                String ptype2 = moveData.getJSONObject("type").getString("url");
                String[] atype2 = ptype2.split("/");
                int typeid = Integer.parseInt(atype2[atype2.length - 1]);

                Move move = new Move(id, name, power, accuracy, typeid, damage);

                moves.put(id, move);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
