package exceptions;

/**
 * Exception that can be raised if something tries to interact with a defeated character in a battle.
 *
 * Developed by: Luis Marin
 */
public class DefeatedCharacterException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add.
     */
    public DefeatedCharacterException(String message) {
        super(message);
    }
}
