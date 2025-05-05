package modifiers;

/**
 * Base class for all the Modifiers that the Entities can have.
 * Every modifier should have its definition for every attribute it modifies.
 * Take in mind a modifier adds or subtract the current Entity parameters.
 *
 * Developed by: Luis Marin
 */
public abstract class Modifiers {

    private int points;
    private boolean active;

    /**
     * Creates a new modifier with the given influence points.
     * @param points Points of influence based on the Definition.
     */
    public Modifiers(int points) {
        this.points = points;
        this.active = false;
    }

    /**
     * Return the points of this Modifier.
     * @return Points of modifier.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of this modifier with the given points as a parameter.
     * @param points Points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns whether the current modifier was activated and has affected its host Entity.
     * @return True if is active, False otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the state of this Modifier.
     */
    public void setActive() {
        this.active = true;
    }
}
