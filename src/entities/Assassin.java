package entities;

import modifiers.Damage;
import modifiers.Dexterity;
import modifiers.Modifiers;

/**
 * Assassin class is an Entity that has the following stats:
 * Health: 75
 * Damage: 15
 * Defense: 5
 * Speed: 25
 * Dexterity: 30
 *
 * Its special ability is increase its damage by 5 and dexterity by 20 for one turn.
 *
 * Developed by: Luis Marin
 */
public class Assassin extends Entity{

    /**
     * Creates a new Assassin with its basic stats.
     */
    public Assassin() {
        super(75, 15, 5, 25, 30);
    }

    /**
     * Focuses on enemies movements increasing its dexterity to avoid attacks by 20 and its damage by 5.
     */
    @Override
    public void specialAbility() {
        Modifiers intelligence = new Dexterity(20);
        Modifiers rage = new Damage(5);
        this.addModifier(intelligence);
        this.addModifier(rage);
    }

    @Override
    public String toString() {
        return String.format("%s | Hp: %d" , "Assassin", getHealth());
    }
}
