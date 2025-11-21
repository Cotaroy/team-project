package entity;

public class Move {
    private int id;
    private String name;
    private int power;
    private int accuracy;
    private int type_id;
    private String damage;

    public Move(int id, String name, int power, int accuracy, int type_id, String damage) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type_id = type_id;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return name;
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
    public int getId() { return id; }
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
    public int getType_id() {
        return type_id;
    }
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public String getDamage() {
        return damage;
    }
    public void setDamage(String damage) {
        this.damage = damage;
    }

}
