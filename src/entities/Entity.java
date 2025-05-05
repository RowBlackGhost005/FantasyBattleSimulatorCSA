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

    /**
     * Creates an Entity with all its parameters initialized at the given as parameters.
     * @param health Health of this Entity.
     * @param damage Damage points of this Entity.
     * @param defense Defense of this Entity.
     * @param speed Speed of this Entity.
     * @param dexterity Dexterity of this Entity.
     */
    public Entity(int health, int damage, int defense, int speed, int dexterity) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.dexterity = dexterity;
        this.modifiers = new ArrayList<>();
        this.energy = 0;
    }

    /**
     * Returns the Health of this Entity after applying all Modifiers available.
     * @return Current Health of this Entity.
     */
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

    /**
     * Sets the Health of this Entity at the given as parameter.
     * @param health Health to set at this Entity.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the Damage points of this Entity after applying all Modifiers available.
     * @return Current Damage points of this Entity
     */
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

    /**
     * Sets the Damage to this Entity at the given as parameter.
     * @param damage Damage to set at this Entity.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Returns the Defense of this Entity after applying all Modifiers available
     * @return Current Defense of this Entity
     */
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

    /**
     * Sets the Defense of this Entity at the given as parameter.
     * @param defense Defense to set at this Entity.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Returns the speed of this Entity
     * @return Speed of this Entity.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of this Entity at the given as parameter.
     * @param speed Speed to set at this Entity.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns the Dexterity of this entity after applying the available Modifications.
     * @return Current Dexterity of this Entity.
     */
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

    /**
     * Sets this Entity dexterity to the given as parameter
     * @param dexterity Dexterity to set at this Entity.
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Returns whether this Entity is defeated or not.
     * An Entity is defeated if his health points are zero or less.
     * @return True if the Entity is defeated, False otherwise
     */
    public boolean isDefeated(){
        return this.health <= 0;
    }

    /**
     * Adds a modifier to this Entity.
     * @param modifier Modifier to add.
     */
    public void addModifier(Modifiers modifier) {
        this.modifiers.add(modifier);
    }

    /**
     * Returns the current energy of this Entity.
     * @return Energy of this entity.
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * Sets the energy of this Entity at the given as parameter.
     * @param energy Energy to set.
     */
    public void setEnergy(byte energy){
        this.energy = energy;
    }

    /**
     * Adds one to the current energy of this Entity.
     * No more energy is added if its already 5.
     */
    public void rechargeEnergy(){
        if(energy < 5){
            this.energy++;
        }
    }

    /**
     * Method to override to set the special ability of the concrete class of this Entity base.
     */
    public abstract void specialAbility();
}
