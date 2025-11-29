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

    /**
     * Get the name with proper noun capitalization.
     * @return the name instance variable but capitalized after spaces or dashes
     */
    public String capitalize() {
        final String splitBy = "-";
        String result = "";
        if (name.split(splitBy).length == 1) {
            result = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        else {
            final StringBuilder nme = new StringBuilder();
            for (int i = 0; i < name.split(splitBy).length; i++) {
                final String unit = name.split(splitBy)[i].substring(0, 1).toUpperCase()
                        + name.split(splitBy)[i].substring(1);
                nme.append(unit);
                nme.append(" ");
            }
            nme.delete(nme.length() - 1, nme.length());
            result = nme.toString();
        }
        return result;
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
