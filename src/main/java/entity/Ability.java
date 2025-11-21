package entity;

public class Ability {
    private String name;
    private int id;
    private String description;

    public Ability(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getProperName() {
        return capitalize() + ": " + description;
    }

    public String capitalize() {
        if (name.split("-").length == 1) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        else {
            StringBuilder nme = new StringBuilder();
            for (int i = 0; i < name.split("-").length; i++)
            {
                String unit = name.split("-")[i].substring(0, 1).toUpperCase() + name.split("-")[i].substring(1);
                 nme.append(unit);
                 nme.append(" ");
            }
            nme.delete(nme.length() - 1, nme.length());
            String result = nme.toString();
            return result;
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
