package entities;

import modifiers.Defense;
import modifiers.Modifiers;

/**
 * Mage class is an Entity that has the following stats:
 * Health: 80
 * Damage: 30
 * Defense: 0
 * Speed: 30
 * Dexterity: 15
 *
 * Its special ability is increase its defense by 20 for one turn.
 *
 * Developed by: Luis Marin
 */
public class Mage extends Entity {

    /**
     * Creates a new Mage with its base stats.
     */
    public Mage() {
        super(80, 30, 0, 30, 15);
    }

    /**
     * Generates a magic shield around this mage, increasing its defense by 20.
     */
    @Override
    public void specialAbility() {
        Modifiers guardUp = new Defense(20);
        this.addModifier(guardUp);
    }

    @Override
    public String toString() {
        return String.format("%s | Hp: %d" , "Mage", getHealth());
    }
}
