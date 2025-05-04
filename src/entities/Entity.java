package entities;

public abstract class Entity {

    private int health;
    private int damage;
    private int defense;
    private int speed;
    private int dexterity;

    public Entity(int health, int damage, int defense, int speed, int dexterity) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.dexterity = dexterity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

}
