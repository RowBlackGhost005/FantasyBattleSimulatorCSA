package exceptions;

/**
 * Exception that can be raised if a character wants to use its special ability but has not enough energy for it.
 *
 * Developed by: Luis Marin
 */
public class OutOfEnergyException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add.
     */
    public OutOfEnergyException(String message) {
        super(message);
    }
}
