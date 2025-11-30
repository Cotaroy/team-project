package entity;

public class Move {
    private int id;
    private String name;
    private int power;
    private int accuracy;
    private int typeId;
    private String damage;

    public Move(int id, String name, int power, int accuracy, int typeId, String damage) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.typeId = typeId;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return name;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

}
