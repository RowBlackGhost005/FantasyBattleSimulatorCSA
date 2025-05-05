package entities;

import modifiers.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Entity class is the base class for all the Characters that can be inside a battle.
 * This class provides functionality for modifiers that can effect any Entity at any time in the battle.
 *
 * Developed by: Luis Marin
 */
public abstract class Entity {

    private int health;
    private int damage;
    private int defense;
    private int speed;
    private int dexterity;
    private List<Modifiers> modifiers;
    private byte energy;

    public Entity(int health, int damage, int defense, int speed, int dexterity) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.dexterity = dexterity;
        this.modifiers = new ArrayList<>();
        this.energy = 0;
    }

    public int getHealth() {
        Iterator<Modifiers> iterator = modifiers.iterator();

        while(iterator.hasNext()){
            Modifiers modifier = iterator.next();

            if(modifier instanceof Health){
                health += modifier.getPoints();
                iterator.remove();
            }
        }

        return Math.max(health, 0);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {

        int tempDamage = damage;

        Iterator<Modifiers> iterator = modifiers.iterator();

        while(iterator.hasNext()){
            Modifiers modifier = iterator.next();

            if(modifier instanceof Damage){
                tempDamage += modifier.getPoints();
                iterator.remove();
            }
        }

        return tempDamage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {

        int tempDefense = defense;

        Iterator<Modifiers> iterator = modifiers.iterator();

        while(iterator.hasNext()){
            Modifiers modifier = iterator.next();

            if(modifier instanceof Defense){
                tempDefense += modifier.getPoints();
                iterator.remove();
            }
        }

        return tempDefense;
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
        int tempDexterity = dexterity;

        Iterator<Modifiers> iterator = modifiers.iterator();

        while(iterator.hasNext()){
            Modifiers modifier = iterator.next();

            if(modifier instanceof Dexterity){
                tempDexterity += modifier.getPoints();
                iterator.remove();
            }
        }

        return tempDexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public boolean isDefeated(){
        return this.health <= 0;
    }

    public void addModifier(Modifiers modifier) {
        this.modifiers.add(modifier);
    }

    public int getEnergy(){
        return energy;
    }

    public void setEnergy(byte energy){
        this.energy = energy;
    }

    public void rechargeEnergy(){
        if(energy < 5){
            this.energy++;
        }
    }

    public abstract void specialAbility();
}
