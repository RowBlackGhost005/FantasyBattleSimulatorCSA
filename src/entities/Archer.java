package entities;

import modifiers.Damage;
import modifiers.Modifiers;

/**
 * Archer class is an Entity that has the following stats:
 * Health: 50
 * Damage: 35
 * Defense: 0
 * Speed: 30
 * Dexterity: 20
 *
 * Its special ability is increase its damage by 5 for one turn.
 *
 * Developed by: Luis Marin
 */
public class Archer extends Entity{

    /**
     * Creates a new Archer with its base stats.
     */
    public Archer() {
        super(50, 35, 0, 30, 20);
    }

    /**
     * Focuses to shoot a perfect arrow to its enemies increasing its damage by 5.
     */
    @Override
    public void specialAbility() {
        Modifiers rage = new Damage(5);
        this.addModifier(rage);
    }

    @Override
    public String toString() {
        return String.format("%s | Hp: %d" , "Archer", getHealth());
    }
}
