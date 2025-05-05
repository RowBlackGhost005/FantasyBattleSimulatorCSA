package exceptions;

/**
 * Exception that can be raised if the user inputs invalid data based on the given context.
 * i.e. Input a string while being asked for an integer.
 *
 * Developed by: Luis Marin
 */
public class InvalidInputException extends Exception {

    /**
     * Creates a new exception with the given message.
     * @param message Message to add.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
