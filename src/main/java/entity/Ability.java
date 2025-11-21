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
