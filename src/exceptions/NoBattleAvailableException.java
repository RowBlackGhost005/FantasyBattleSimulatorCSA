package exceptions;

/**
 * Exception that can be raised when trying to initialize a battle where there is no battle created yet.
 *
 * Developed by: Luis Marin
 */
public class NoBattleAvailableException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add
     */
    public NoBattleAvailableException(String message) {
        super(message);
    }
}
