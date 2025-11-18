package entity;

public class Move {
    private String name;
    private int power;
    private int accuracy;
    private String effect;
    private int type_id;
    private int damage;

    public Move(String name, int power, int accuracy, String effect, int type_id, int damage) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.effect = effect;
        this.type_id = type_id;
        this.damage = damage;
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
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public int getType_id() {
        return type_id;
    }
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
