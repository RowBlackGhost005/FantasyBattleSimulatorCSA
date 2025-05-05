package exceptions;

/**
 * Exception that can be raised if the player tries to initialize a battle that has been finished.
 *
 * Developed by: Luis Marin
 */
public class BattleOverException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add.
     */
    public BattleOverException(String message) {
        super(message);
    }
}
