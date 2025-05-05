package entities;

import modifiers.Damage;
import modifiers.Defense;
import modifiers.Modifiers;

/**
 * Knight class is an Entity that has the following stats:
 * Health: 100
 * Damage: 20
 * Defense: 5
 * Speed: 25
 * Dexterity: 10
 *
 * Its special ability is increase its damage by 5 and defense by 10 for one turn.
 *
 * Developed by: Luis Marin
 */
public class Knight extends Entity{

    /**
     * Creates a new Knight with its base stats.
     */
    public Knight() {
        super(100, 20, 5, 25, 10);
    }

    /**
     * Guards up and prepares for battle increasing its defense by 10 and damage by 5.
     */
    @Override
    public void specialAbility() {
        Modifiers guardUp = new Defense(10);
        Modifiers rage = new Damage(5);
        this.addModifier(guardUp);
        this.addModifier(rage);
    }

    @Override
    public String toString() {
        return String.format("%s | Hp: %d" , "Knight", getHealth());
    }

}
