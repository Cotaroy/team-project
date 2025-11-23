package dataaccess;

import entity.Ability;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbilityMap {
    private final Map<Integer, Ability> abilities = new HashMap<>();

    public Ability getAbility(int id) {
        return abilities.get(id);
    }

    public AbilityMap() {
        try {
            String jsonText = new String(
                    getClass().getClassLoader().getResourceAsStream("abilities.json").readAllBytes()
            );

            // Parse as JSON object
            JSONObject root = new JSONObject(jsonText);

            // Convert dictionary values to an array
            JSONArray jsonArray = new JSONArray();

            for (String key : root.keySet()) {
                jsonArray.put(root.getJSONObject(key));
            }

            for (int i = 1; i < jsonArray.length(); i++) {

                JSONObject abilityData = jsonArray.getJSONObject(i);
                int id = abilityData.getInt("id");

                String name = abilityData.getString("name");
                JSONArray effects = abilityData.getJSONArray("effect_entries");
                String description = "";

                for (int j = 0; j < effects.length(); j++) {
                    JSONObject entry = effects.getJSONObject(j);
                    if (entry.getJSONObject("language").getString("name").equals("en")) {
                        description = entry.getString("short_effect");
                        break;
                    }
                }
                Ability ability = new Ability(name, id, description);

                abilities.put(id, ability);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
