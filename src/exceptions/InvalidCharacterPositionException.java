package exceptions;

/**
 * Exception that can be raised if something tries to interact with an invalid position in a battle.
 * i.e. Trying to attack character 3 out of 2
 *
 * Developed by: Luis Marin
 */
public class InvalidCharacterPositionException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add.
     */
    public InvalidCharacterPositionException(String message) {
        super(message);
    }
}
